package com.vm.eea.data.model

import androidx.room.*
import com.vm.eea.domain.*
import com.vm.eea.domain.load.FeedingMode
import com.vm.eea.domain.load.LoadId
import com.vm.eea.domain.load.Motor
import com.vm.eea.domain.load.LoadType

@Entity(tableName = "loads",
    foreignKeys = [
        ForeignKey(entity = ProjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["code","projectId"], unique = true), Index(value = ["powerSupplyPath"])]
)
data class LoadEntity(val code:String,
                      val description:String,
                      @Embedded(prefix = "power_")
                      val power: Power,
                      @Embedded(prefix = "powerfactor_")
                      val powerfactor: PowerFactor,
                      @Embedded(prefix = "demand_factor_")
                      val demandFactor: PowerFactor,
                      @Embedded(prefix = "efficiency_")
                      val efficiency: Efficiency,
                      val system: PowerSystem,
                      val serviceMode: ServiceMode,
                      val loadType: LoadType,
                      val powerSupplyPath:String,
                      val normal:Boolean,
                      val emergency:Boolean,
                      val projectId:Long,
                      @PrimaryKey(autoGenerate = true) val id:Long=0){

    fun toMotorDomain()=Motor(code,description,power,powerfactor,
        demandFactor,efficiency,system,serviceMode,projectId,SupplyPath(powerSupplyPath),
        FeedingMode(normal,emergency), LoadId(id))
}
