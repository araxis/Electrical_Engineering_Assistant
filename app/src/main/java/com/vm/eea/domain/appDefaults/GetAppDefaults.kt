package com.vm.eea.domain.appDefaults

import com.vm.eea.domain.*

class GetAppDefaults(private val appDefaultsRepository: IAppDefaultsRepository) {

    suspend operator fun invoke(): AppDefaults {
        return appDefaultsRepository.getDefaults()
    }

}
data class AppDefaults(
    val powerSystem: PowerSystem,
    val unitOfOfVoltage: UnitOfVoltage,
    val unitOfOfPower: UnitOfPower,
    val unitOfOfLength: UnitOfLength,
    val unitOfOfTemperature: UnitOfTemperature,
    val unitOfWireSize: UnitOfWireSize,
    val singlePhaseVoltage: Voltage,
    val twoPhaseVoltage: Voltage,
    val threePhaseVoltage: Voltage,
    val altitude: Length,
    val methodOfInstallation: MethodOfInstallation,
    val ambientTemperature: Temperature,
    val groundTemperature: Temperature,
    val soilResistivity: ThermalResistivity,
    val conductor: Conductor,
    val insulation: Insulation,
    val singlePhasePowerFactor: PowerFactor,
    val twoPhasePowerFactor: PowerFactor,
    val threePhasePowerFactor: PowerFactor,
    val panelToPanelMaxVoltDrop: VoltDrop,
    val panelToMotorMaxVoltDrop: VoltDrop,
    val circuitInTheSameConduit:CircuitCount,
    val maxWireSize: WireSize,
    val minWireSize: WireSize,
    val standard: Standard,
    val id: Long)