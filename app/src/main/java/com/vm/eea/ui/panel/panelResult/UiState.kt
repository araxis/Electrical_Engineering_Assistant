package com.vm.eea.ui.panel.panelResult

import com.vm.eea.ui.GroupPropertyItem

data class UiState(val resultItems:List<GroupPropertyItem> = listOf(),
                   val report:String="",val preview:Boolean=false)