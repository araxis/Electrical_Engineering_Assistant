package com.vm.eea.ui.project.projectDetailsView

import com.vm.eea.ui.PropertyItem

data class ProjectDetailsState(val navigationItems:List<PropertyItem>){
    companion object {
        fun init() = ProjectDetailsState(emptyList())
    }
}
