package com.vm.eea.application.project

import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.SelectableItem

interface IGetProjectMethodOfInstallation {
    suspend operator fun invoke(projectId: ProjectId):List<SelectableItem<MethodOfInstallation>>
}