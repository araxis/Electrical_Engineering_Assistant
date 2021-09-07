package com.vm.eea.ui.project.updateProjectVoltage

import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.UnitOfVoltage
import com.vm.eea.domain.Voltage
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.IText

data class UiState(val pageTitle:String, val value:String,
                   val unit: UnitOfVoltage,
                   val error: IText?,
                   val canExecute:Boolean,
                   val addToDefaults:Boolean,
                   val defaults:List<SelectableItem<Voltage>>){
    companion object{
        fun init(system: PowerSystem) =when(system){
            PowerSystem.SinglePhase -> UiState("1-phase voltage","",
                UnitOfVoltage.V,
                null,
                canExecute = false,
                addToDefaults = true,
                defaults = emptyList())
            PowerSystem.ThreePhase -> UiState("3-phase voltage","",
                UnitOfVoltage.V,
                null,
                canExecute = false,
                addToDefaults = true,
                defaults = emptyList())
            PowerSystem.TwoPhase -> TODO()

        }
    }
}


