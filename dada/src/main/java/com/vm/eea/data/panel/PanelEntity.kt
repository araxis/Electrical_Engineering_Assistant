package com.vm.eea.data.panel

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.vm.eea.application.CosPhi
import com.vm.eea.data.project.ProjectEntity

@Entity(tableName = "panels",
    foreignKeys = [
    ForeignKey(
        entity = ProjectEntity::class,
        parentColumns = ["id"],
        childColumns = ["projectId"],
        onDelete = CASCADE
    )
],
    indices = [Index(value = ["code","projectId"], unique = true), Index(value = ["powerSupplyPath"])]
)
data class PanelEntity(@ColumnInfo(name = "projectId",index = true) val projectId: Long,
                       val code: String,
                       val description:String,
                       val isMdp:Boolean,
                       @Embedded(prefix = "demand_factor_")
                       val demandFactor: CosPhi,
                       val coincidenceFactor: Double,
                       val powerSupplyPath:String="/",
                       @PrimaryKey(autoGenerate = true)  val id: Long=0)  {


}