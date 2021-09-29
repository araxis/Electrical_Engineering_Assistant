package com.vm.eea.application.panel

import com.vm.eea.application.project.ProjectId

interface IGetSimplePanels {
    suspend operator fun invoke(projectId: ProjectId):List<SimplePanel>
}

