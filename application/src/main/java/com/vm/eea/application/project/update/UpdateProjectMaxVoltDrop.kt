package com.vm.eea.application.project.update

import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.RelationType
import com.vm.eea.application.VoltDrop
import com.vm.eea.application.project.ProjectId

class UpdateProjectMaxVoltDrop(private val repository: IProjectRepository) {
     suspend operator fun invoke(projectId: ProjectId, value: VoltDrop, relationType: RelationType) {
        when(relationType){
            RelationType.PanelToPanel -> repository.updatePanelToPanelMaxVoltDrop(projectId,value)
            RelationType.PanelToMotor -> repository.updatePanelToMotorMaxVoltDrop(projectId,value)
        }
    }
}