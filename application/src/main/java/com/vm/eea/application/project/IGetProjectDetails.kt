package com.vm.eea.application.project

import com.vm.eea.application.*
import kotlinx.coroutines.flow.Flow

interface IGetProjectDetails {
    operator fun invoke(projectId: ProjectId): Flow<ProjectDetails>

}

class ProjectDetails(val id: ProjectId,
                     val code:String,
                     val description: String,
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
                     val panelToPanelMaxVoltDrop: VoltDrop,
                     val panelToMotorMaxVoltDrop: VoltDrop,
                     val circuitInTheSameConduit: CircuitCount,
                     val maxWireSize: WireSize,
                     val minWireSize: WireSize,
                     val standard: Standard,
                     val totalCurrent: Current,
                     val totalApparentPower: ApparentPower,
                     val totalReactivePower: ReactivePower,
                     val totalActivePower: Power
) {

    val cosPhi=totalActivePower/totalApparentPower

}