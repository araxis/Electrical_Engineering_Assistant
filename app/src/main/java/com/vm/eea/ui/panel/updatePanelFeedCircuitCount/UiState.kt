package com.vm.eea.ui.panel.updatePanelFeedCircuitCount

import com.vm.eea.domain.CircuitCount
import com.vm.eea.ui.SelectableItem

data class UiState(val items:List<SelectableItem<CircuitCount>>) {
    companion object{
        fun init()=UiState(emptyList())
    }
}