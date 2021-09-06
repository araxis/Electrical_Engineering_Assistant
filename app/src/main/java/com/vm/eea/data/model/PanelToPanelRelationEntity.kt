package com.vm.eea.data.model

import androidx.room.*
import com.vm.eea.domain.*
import com.vm.eea.domain.panel.Panel
import com.vm.eea.domain.panelToPanelRelation.PanelToPanelRelation

@Entity(tableName = "panel_panel_relations",
    foreignKeys = [
    ForeignKey(entity = PanelEntity::class,
    parentColumns = ["id"],
    childColumns = ["fromPanelId"]),
    ForeignKey(entity = PanelEntity::class,
        parentColumns = ["id"],
        childColumns = ["toPanelId"])
]
)
class PanelToPanelRelationEntity(@ColumnInfo(index = true) val fromPanelId:Long,
                                 @ColumnInfo(index = true)  val toPanelId:Long,
                                 @Embedded(prefix = "length_")
                                 val length: Length,
                                 @Embedded(prefix = "volt_drop_")
                                 val maxVoltageDrop: VoltDrop,
                                 val methodOfInstallation: MethodOfInstallation,
                                 @Embedded(prefix = "ambient_temp_")
                                 val ambientTemperature: Temperature,
                                 @Embedded(prefix = "ground_temp_")
                                 val groundTemperature: Temperature,
                                 @Embedded(prefix = "soil_thermal_resistivity_")
                                 val soilThermalResistivityUnit: ThermalResistivity,
                                 @Embedded(prefix = "circuit_count_")
                     val circuitCount:CircuitCount,
                                 val conductor: Conductor,
                                 val insulation: Insulation,
                                 @PrimaryKey(autoGenerate = true) val id:Long=0) {
    fun toDomain(from:Panel,to: Panel) =PanelToPanelRelation(insulation = insulation,
    conductor = conductor,
     groundTemperature = groundTemperature,
    soilThermalResistivity = soilThermalResistivityUnit,
    toPanelId = to,
    maxVoltageDrop = maxVoltageDrop,
    fromPanel = from,
    id = RelationId(id),
    ambientTemperature = ambientTemperature,
    methodOfInstallation = methodOfInstallation,
    circuitCount=circuitCount,
    length = length)
}