package com.vm.eea.ui.panel.updateFeeder

import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panel.SimplePanel

data class UiState(val feeders:List<SelectableItem<SimplePanel>>) {
    companion object{
        fun init()=UiState(emptyList())
    }
}
