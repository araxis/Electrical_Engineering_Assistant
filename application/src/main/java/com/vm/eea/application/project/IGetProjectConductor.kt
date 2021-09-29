package com.vm.eea.application.project

import com.vm.eea.application.Conductor
import com.vm.eea.application.SelectableItem


interface IGetProjectConductor {
    suspend operator fun invoke(projectId: ProjectId):List<SelectableItem<Conductor>>
}