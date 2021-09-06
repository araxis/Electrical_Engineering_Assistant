package com.vm.eea.domain.project

import com.vm.eea.domain.RelationType
import com.vm.eea.domain.VoltDrop

class UpdateProjectMaxVoltDrop(private val projectRepository:IProjectRepository) {

    suspend operator fun invoke(projectId:Long, value:VoltDrop,relationType: RelationType){

        when(relationType) {
            RelationType.PanelToPanel ->
                projectRepository.updatePanelToPanelMaxVoltDrop(projectId,value)
            RelationType.PanelToMotor ->
                projectRepository.updatePanelToMotorMaxVoltDrop(projectId,value)
        }

    }
}

