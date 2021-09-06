package com.vm.eea.ui.panel.updatePanelFeedConductor

import com.vm.eea.domain.Conductor
import com.vm.eea.ui.SelectableItem

data class UiState(val items:List<SelectableItem<Conductor>>) {
    companion object{
        fun init()=UiState(emptyList())
    }
}