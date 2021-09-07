package com.vm.eea.data.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.vm.eea.domain.SupplyPath
import com.vm.eea.domain.panel.Panel

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
                   val powerSupplyPath:String="/",
                  @PrimaryKey(autoGenerate = true)  val id: Long=0)  {

    fun toDomain()= Panel(projectId,code,description,isMdp, SupplyPath(powerSupplyPath),id)
}