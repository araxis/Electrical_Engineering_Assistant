package com.vm.eea.ui.panel.updateFeeder

data class UiState(val feeders:List<FeederItem>) {
    companion object{
        fun init()=UiState(emptyList())
    }
}
