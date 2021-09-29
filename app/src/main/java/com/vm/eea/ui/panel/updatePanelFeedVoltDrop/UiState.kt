package com.vm.eea.ui.panel.updatePanelFeedVoltDrop

import com.vm.eea.application.Temperature
import com.vm.eea.application.SelectableItem
import com.vm.eea.utilities.IText

data class UiState(val value:String, val defaults:List<SelectableItem<Temperature>>, val canExecute:Boolean, val error: IText? ) {

    companion object{
        fun init()= UiState("", emptyList(), false, null)
    }
}