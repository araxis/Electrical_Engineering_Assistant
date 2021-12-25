package com.vm.eea.application.project

import com.vm.eea.application.*
data class Project(
    val projectId: ProjectId,
    val code:String,
                   val description: String,
                   val lineToNullVoltage: Double,
                   val lineToLineVoltage: Double,
                   val altitude: Length,
                   val methodOfInstallation: MethodOfInstallation,
                   val ambientTemperature: Temperature,
                   val groundTemperature: Temperature,
                   val soilResistivity: ThermalResistivity,
                   val conductor: Conductor,
                   val insulation: Insulation,
                   val panelToPanelMaxVoltDrop: VoltDrop,
                   val panelToMotorMaxVoltDrop: VoltDrop,
                   val circuitInTheSameConduit: CircuitCount,
                   val maxWireSize: WireSize,
                   val minWireSize: WireSize,
                   val standard: Standard)