package com.vm.eea.ui.panel.updatePanelFeedConductor

import com.vm.eea.application.Conductor
import com.vm.eea.application.SelectableItem

data class UiState(val items:List<SelectableItem<Conductor>>) {
    companion object{
        fun init()=UiState(emptyList())
    }
}