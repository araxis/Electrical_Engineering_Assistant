package com.vm.eea.ui.panel.updatePanelFeedSoilResistivity

import com.vm.eea.domain.ThermalResistivity
import com.vm.eea.domain.UnitOfThermalResistivity
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.IText

data class UiState(val pageTitle:String,
                   val value:String,
                   val unit:UnitOfThermalResistivity,
                   val defaults:List<SelectableItem<ThermalResistivity>>,
                   val canExecute:Boolean, val error: IText? ) {

    companion object{

            fun init()=UiState("Thermal resistivity",
                "",
                UnitOfThermalResistivity.MW,
                emptyList(),
                false,
                null)

    }
}