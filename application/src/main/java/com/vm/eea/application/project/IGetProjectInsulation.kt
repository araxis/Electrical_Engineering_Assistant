package com.vm.eea.application.project

import com.vm.eea.application.Insulation
import com.vm.eea.application.SelectableItem

interface IGetProjectInsulation {
    suspend operator fun invoke(projectId: ProjectId):List<SelectableItem<Insulation>>
}