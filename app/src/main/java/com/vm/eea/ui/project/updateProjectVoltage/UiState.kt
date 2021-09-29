package com.vm.eea.ui.project.updateProjectVoltage

import com.vm.eea.application.PowerSystem
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.Voltage
import com.vm.eea.utilities.IText

data class UiState(val pageTitle:String, val value:String,
                   val unit: Voltage.Unit,
                   val error: IText?,
                   val canExecute:Boolean,
                   val addToDefaults:Boolean,
                   val defaults:List<SelectableItem<Voltage>>){
    companion object{
        fun init(system: PowerSystem) =when(system){
            PowerSystem.SinglePhase -> UiState("Single phase voltage","",
                Voltage.Unit.V,
                null,
                canExecute = false,
                addToDefaults = true,
                defaults = emptyList())
            PowerSystem.ThreePhase -> UiState("Three phase voltage","",
                Voltage.Unit.V,
                null,
                canExecute = false,
                addToDefaults = true,
                defaults = emptyList())
            PowerSystem.TwoPhase -> UiState("Two phase voltage","",
                Voltage.Unit.V,
                null,
                canExecute = false,
                addToDefaults = true,
                defaults = emptyList())

        }
    }
}


