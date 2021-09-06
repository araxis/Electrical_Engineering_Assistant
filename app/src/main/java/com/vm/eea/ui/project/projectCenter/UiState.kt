package com.vm.eea.ui.project.projectCenter

import com.vm.eea.domain.panel.Panel
import com.vm.eea.ui.models.SimpleMotor

data class UiState(val currentTab: ProjectCenterTab, val panels:List<Panel>, val motors:List<SimpleMotor>) {
    companion object{
        fun init()=UiState(ProjectCenterTab.Panels, emptyList(), emptyList())
    }
}