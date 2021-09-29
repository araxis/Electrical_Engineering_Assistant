package com.vm.eea.application.project

import com.vm.eea.application.VoltDrop


interface IGetProjectMaxVoltDrop {

    suspend operator fun invoke(projectId: ProjectId, relationType: RelationType): VoltDrop


}