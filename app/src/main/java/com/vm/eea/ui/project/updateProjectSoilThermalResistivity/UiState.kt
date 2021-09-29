package com.vm.eea.ui.project.updateProjectSoilThermalResistivity

import com.vm.eea.application.SelectableItem
import com.vm.eea.application.ThermalResistivity
import com.vm.eea.application.UnitOfThermalResistivity
import com.vm.eea.utilities.IText

data class UiState(val value:String,
                   val unit: UnitOfThermalResistivity,
                   val error: IText?,
                   val canExecute:Boolean,
                   val addToDefaults:Boolean,
                   val defaults:List<SelectableItem<ThermalResistivity>>){
    companion object{
        fun init() = UiState("",
            UnitOfThermalResistivity.MW,
            null,
            canExecute = false,
            addToDefaults = true,
            defaults = emptyList())
    }
}


