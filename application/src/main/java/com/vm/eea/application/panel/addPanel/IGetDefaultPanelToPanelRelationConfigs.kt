package com.vm.eea.application.panel.addPanel

import com.vm.eea.application.project.ProjectId

interface IGetDefaultPanelToPanelRelationConfigs {

    suspend operator fun invoke(projectId: ProjectId):PanelToPanelRelationConfig

}