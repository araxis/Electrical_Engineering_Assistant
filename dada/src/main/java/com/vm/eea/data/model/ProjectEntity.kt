package com.vm.eea.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vm.eea.domain.*
import com.vm.eea.domain.project.Project
import com.vm.eea.domain.project.ProjectId


@Entity(tableName = "projects")
data class ProjectEntity(
    val code:String,
    val description: String,
    val unitOfVoltage: UnitOfVoltage,
    val unitOfOfPower: UnitOfPower,
    val unitOfLength: UnitOfLength,
    val unitOfTemperature: UnitOfTemperature,
    val unitOfWireSize: UnitOfWireSize,
    @Embedded(prefix = "one_phase_voltage_")
    val singlePhaseVoltage: Voltage,
    @Embedded(prefix = "two_phase_voltage_")
    val twoPhaseVoltage: Voltage,
    @Embedded(prefix = "three_phase_voltage_")
    val threePhaseVoltage: Voltage,
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
    @Embedded(prefix = "single_phase_powerfactor_")
    val singlePhasePowerFactor: PowerFactor,
    @Embedded(prefix = "two_phase_powerfactor_")
    val twoPhasePowerFactor: PowerFactor,
    @Embedded(prefix = "three_phase_powerfactor_")
    val threePhasePowerFactor: PowerFactor,
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


){
    fun toDomain()= Project(singlePhaseVoltage = singlePhaseVoltage,
    twoPhaseVoltage = twoPhaseVoltage,
     code = code,
    description = description,
    id = ProjectId(id),
    methodOfInstallation = methodOfInstallation,
    ambientTemperature = ambientTemperature,
    groundTemperature = groundTemperature,
    conductor = conductor,
    insulation = insulation,
    maxWireSize = maxWireSize,
    unitOfWireSize = unitOfWireSize,
     unitOfLength = unitOfLength,
    unitOfTemperature = unitOfTemperature,
    unitOfPower = unitOfOfPower,
    panelToMotorMaxVoltDrop = panelToMotorMaxVoltDrop,
    panelToPanelMaxVoltDrop = panelToPanelMaxVoltDrop,
    soilResistivity = soilResistivity,
    altitude = altitude,
    threePhasePowerFactor = threePhasePowerFactor,
    threePhaseVoltage = threePhaseVoltage,
    standard = standard,
    singlePhasePowerFactor = singlePhasePowerFactor,
    circuitInTheSameConduit = circuitInTheSameConduit,
    isDeleted = isDeleted,
    minWireSize = minWireSize,
    twoPhasePowerFactor = twoPhasePowerFactor,
    unitOfOfVoltage = unitOfVoltage)
}