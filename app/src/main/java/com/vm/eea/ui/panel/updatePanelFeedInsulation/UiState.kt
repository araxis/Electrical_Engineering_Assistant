package com.vm.eea.ui.panel.updatePanelFeedInsulation

import com.vm.eea.application.Insulation
import com.vm.eea.application.SelectableItem

data class UiState(val items:List<SelectableItem<Insulation>>){
    companion object{
        fun int()=UiState(emptyList())
    }
}
