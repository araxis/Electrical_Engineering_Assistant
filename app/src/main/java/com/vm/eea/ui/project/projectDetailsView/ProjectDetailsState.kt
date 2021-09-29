package com.vm.eea.ui.project.projectDetailsView

import com.vm.eea.ui.PropertyItem

data class ProjectDetailsState(val navigationItems:List<PropertyItem> = emptyList(), val calculatedInfo:List<Pair<String,String>> = emptyList())