package com.vm.eea.ui.panel.updatePanelFeedTemperature


import com.vm.eea.application.Environment
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.Temperature
import com.vm.eea.application.UnitOfTemperature
import com.vm.eea.utilities.IText

data class UiState(val pageTitle:String,
                   val value:String,
                   val unit: UnitOfTemperature,
                   val defaults:List<SelectableItem<Temperature>>,
                   val canExecute:Boolean, val error: IText? ) {

    companion object{

            fun init(environment: Environment)=when(environment){
                Environment.Ambient -> UiState("Ambient temperature",
                    "",
                    UnitOfTemperature.C,
                    emptyList(),
                    false,
                    null)
                Environment.Ground -> UiState("Ground temperature",
                    "",
                    UnitOfTemperature.C,
                    emptyList(),
                    false,
                    null)
            }

    }
}