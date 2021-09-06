package com.vm.eea.ui.panel.panelDetails

import com.vm.eea.ui.PropertyItem

data class UiState(val items:List<PropertyItem>){
    companion object {
        fun init() = UiState(emptyList())
    }
}