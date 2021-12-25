package com.vm.eea.application.project

import com.vm.eea.application.PowerSystem
import com.vm.eea.application.Voltage
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.ProjectId

class UpdateProjectVoltage(private val repository: IProjectRepository) {
     suspend operator fun invoke(projectId: ProjectId, value: Voltage, system: PowerSystem) {
        val voltageInVolt=(value to Voltage.Unit.V).value
        when(system){
            PowerSystem.SinglePhase -> repository.updateLineToNullVoltage(projectId,voltageInVolt)
            PowerSystem.TwoPhase ,PowerSystem.ThreePhase-> repository.updateLineToLineVoltage(projectId,voltageInVolt)
        }
    }
}