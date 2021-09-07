package com.vm.eea.data.model

import androidx.room.*
import com.vm.eea.domain.*
import com.vm.eea.domain.LoadId
import com.vm.eea.domain.PanelId
import com.vm.eea.domain.panelToMotorRelation.PanelToMotorRelation

@Entity(tableName = "panel_motor_relations",
    foreignKeys = [
    ForeignKey(entity = PanelEntity::class,
    parentColumns = ["id"],
    childColumns = ["fromPanelId"]),
    ForeignKey(entity = LoadEntity::class,
        parentColumns = ["id"],
        childColumns = ["toLoadId"])
]
)
class PanelToMotorRelationEntity(@ColumnInfo(index = true) val fromPanelId:Long,
                                 @ColumnInfo(index = true)  val toLoadId:Long,
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
                                  val circuitCount: CircuitCount,
                                 val conductor: Conductor,
                                 val insulation: Insulation,
                                 @PrimaryKey(autoGenerate = true) val id:Long=0) {
    fun toDomain() = PanelToMotorRelation(insulation = insulation,
    conductor = conductor,
     groundTemperature = groundTemperature,
    soilThermalResistivity = soilThermalResistivityUnit,
    toLoadId = LoadId(toLoadId),
    maxVoltageDrop = maxVoltageDrop,
    fromPanelId = PanelId(fromPanelId),
    id = id,
    ambientTemperature = ambientTemperature,
    methodOfInstallation = methodOfInstallation,
    circuitCount=circuitCount,
    length = length)
}