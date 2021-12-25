package com.vm.eea.data.project

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vm.eea.application.*


@Entity(tableName = "projects",
    indices = [Index(value = ["code"],unique = true)])
data class ProjectEntity(
    val code:String,
    val description: String,
    val lineToNullVoltage: Double,
    val lineToLineVoltage: Double,
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
    @Embedded(prefix = "max_wire_size_")
    val maxWireSize: WireSize,
    @Embedded(prefix = "min_wire_size_")
    val minWireSize: WireSize,
    val standard: Standard,
    val isDeleted:Boolean=false,
    @PrimaryKey(autoGenerate = true)  val id: Long=0


)