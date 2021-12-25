package com.vm.eea.data.motor

import androidx.room.*
import com.vm.eea.application.*
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.data.project.ProjectEntity


@Entity(tableName = "loads",
    foreignKeys = [
        ForeignKey(entity = ProjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["projectId"])
    ],
    indices = [Index(value = ["code","projectId"], unique = true), Index(value = ["powerSupplyPath"])]
)
data class LoadEntity(val code:String,
                      val description:String,
                      @Embedded(prefix = "power_")
                      val power: Power,
                      val cosPhi: Double,
                      val sinPhi: Double,
                      val tanPhi: Double,
                      val demandFactorCosPhi: Double,
                      val demandFactorTanPhi:Double,
                      @Embedded(prefix = "efficiency_")
                      val efficiency: Efficiency,
                      val ratedSpeedRpm: Double,
                      val system: PowerSystem,
                      val startMode: StartMode,
                      val serviceMode: ServiceMode,
                      val loadType: LoadType,
                      val powerSupplyPath:String,
                      val normal:Boolean,
                      val emergency:Boolean,
                      val projectId:Long,
                      val powerInWatt:Double,
                      val applyLocalCosPhiCorrection:Boolean=false,
                      val isBiDirect:Boolean=false,
                      val hasOverLoadRelay:Boolean=true,
                      val protectionType: ProtectionType = ProtectionType.CircuitBreaker,
                      @PrimaryKey(autoGenerate = true) val id:Long=0){


}
