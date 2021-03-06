package com.vm.eea.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vm.eea.application.*



@Entity(tableName = "defaults")
data class DefaultsEntity(
    val powerSystem: PowerSystem,
    val lineNullVoltage: Double,
    val lineLineVoltage: Double,
    @Embedded(prefix = "altitude_")
    val altitude: Length,
    val methodOfInstallation: MethodOfInstallation,
    @Embedded(prefix = "ambient_temp_")
    val ambientTemperature: Temperature,
    @Embedded(prefix = "soil_temp_")
    val groundTemperature: Temperature,
    @Embedded(prefix = "soil_thermal_resist_")
    val soilResistivity: ThermalResistivity,
    val conductor: Conductor,
    val insulation: Insulation,
    @Embedded(prefix = "panel_panel_volt_drop_")
    val panelToPanelMaxVoltDrop: VoltDrop,
    @Embedded(prefix = "panel_motor_volt_drop_")
    val panelToMotorMaxVoltDrop: VoltDrop,
    @Embedded(prefix = "circuit_count_")
    val circuitInTheSameConduit: CircuitCount,
    @Embedded(prefix = "max_cable_")
    val maxWireSize: WireSize,
    @Embedded(prefix = "min_cable_")
    val minWireSize: WireSize,
    val standard: Standard,
    @PrimaryKey(autoGenerate = true) val id: Long = 0

 
)