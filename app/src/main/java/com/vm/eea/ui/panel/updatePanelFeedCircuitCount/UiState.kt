package com.vm.eea.ui.panel.updatePanelFeedCircuitCount

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.SelectableItem

data class UiState(val items:List<SelectableItem<CircuitCount>>) {
    companion object{
        fun init()=UiState(emptyList())
    }
}