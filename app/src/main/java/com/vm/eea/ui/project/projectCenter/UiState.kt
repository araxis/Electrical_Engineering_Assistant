package com.vm.eea.ui.project.projectCenter

import com.vm.eea.application.project.IGetProjectPanels
import com.vm.eea.application.project.ProjectMotorsResult

data class UiState(val currentTab: ProjectCenterTab,
                   val panels:List<IGetProjectPanels.Result>,
                   val motors:List<ProjectMotorsResult>) {

    companion object{
        fun init()=UiState(ProjectCenterTab.Panels, emptyList(), emptyList())
    }


    enum class ProjectCenterTab {
        Panels,Motors
    }

}
