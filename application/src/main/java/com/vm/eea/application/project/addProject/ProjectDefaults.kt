package com.vm.eea.application.project.addProject

import com.vm.eea.application.*

data class ProjectDefaults(val powerSystem: PowerSystem,
                           val unitOfOfVoltage: Voltage.Unit,
                           val unitOf: Power.Unit,
                           val unitOfOfLength: Length.Unit,
                           val unitOfOfTemperature: UnitOfTemperature,
                           val unitOfWireSize: UnitOfWireSize,
                           val singlePhaseVoltage: Double,
                           val twoPhaseVoltage: Double,
                           val threePhaseVoltage: Double,
                           val altitude: Length,
                           val methodOfInstallation: MethodOfInstallation,
                           val ambientTemperature: Temperature,
                           val groundTemperature: Temperature,
                           val soilResistivity: ThermalResistivity,
                           val conductor: Conductor,
                           val insulation: Insulation,
                           val singlePhaseCosPhi: CosPhi,
                           val twoPhaseCosPhi: CosPhi,
                           val threePhaseCosPhi: CosPhi,
                           val panelToPanelMaxVoltDrop: VoltDrop,
                           val panelToMotorMaxVoltDrop: VoltDrop,
                           val circuitInTheSameConduit: CircuitCount,
                           val maxWireSize: WireSize,
                           val minWireSize: WireSize,
                           val standard: Standard
)