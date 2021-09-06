package com.vm.eea.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vm.eea.domain.*
import com.vm.eea.domain.appDefaults.AppDefaults


@Entity(tableName = "defaults")
data class DefaultsEntity(
    val powerSystem: PowerSystem,
    val unitOfVoltage: UnitOfVoltage,
    val unitOfPower: UnitOfPower,
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
    val circuitInTheSameConduit:CircuitCount,
    @Embedded(prefix = "max_cable_")
                           val maxWireSize:WireSize,
    @Embedded(prefix = "min_cable_")
                           val minWireSize:WireSize,
    val standard: Standard,
    @PrimaryKey(autoGenerate = true) val id: Long = 0

 
){
    fun toDomainModel(): AppDefaults = AppDefaults(
    powerSystem=powerSystem,
     unitOfOfVoltage=unitOfVoltage,
     unitOfOfPower= unitOfPower,
     unitOfOfLength= unitOfLength,
     unitOfOfTemperature= unitOfTemperature,
     unitOfWireSize= unitOfWireSize,
     singlePhaseVoltage= singlePhaseVoltage,
     twoPhaseVoltage=twoPhaseVoltage,
     threePhaseVoltage= threePhaseVoltage,
     altitude= altitude,
     methodOfInstallation= methodOfInstallation,
     ambientTemperature= ambientTemperature,
     groundTemperature= groundTemperature,
     soilResistivity= soilResistivity,
     conductor= conductor,
     insulation= insulation,
     singlePhasePowerFactor= singlePhasePowerFactor,
     threePhasePowerFactor= threePhasePowerFactor,
     panelToPanelMaxVoltDrop= panelToPanelMaxVoltDrop,
     panelToMotorMaxVoltDrop= panelToMotorMaxVoltDrop,
     circuitInTheSameConduit=circuitInTheSameConduit,
     maxWireSize=maxWireSize,
     minWireSize=minWireSize,
     standard= standard,
    id=id,
        twoPhasePowerFactor = twoPhasePowerFactor
    )
}
