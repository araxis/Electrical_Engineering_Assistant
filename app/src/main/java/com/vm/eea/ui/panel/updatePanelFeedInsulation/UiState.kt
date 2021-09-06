package com.vm.eea.ui.panel.updatePanelFeedInsulation

import com.vm.eea.domain.Insulation
import com.vm.eea.ui.SelectableItem

data class UiState(val items:List<SelectableItem<Insulation>>){
    companion object{
        fun int()=UiState(emptyList())
    }
}
