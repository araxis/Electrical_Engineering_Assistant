package com.vm.eea.data.project

import com.vm.eea.application.VoltDrop
import com.vm.eea.application.project.IGetProjectMaxVoltDrop
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.RelationType
import com.vm.eea.data.AppDatabase

class GetProjectMaxVoltDrop(private val db:AppDatabase):IGetProjectMaxVoltDrop {
    override suspend fun invoke(projectId: ProjectId, relationType: RelationType): VoltDrop {
        return when(relationType){
            RelationType.PanelToPanel -> db.projectReadDao().getProjectPanelToPanelMaxVoltDrop(projectId.id)
            RelationType.PanelToMotor -> db.projectReadDao().getProjectPanelToMotorMaxVoltDrop(projectId.id)
        }.let { VoltDrop(it) }
    }
}