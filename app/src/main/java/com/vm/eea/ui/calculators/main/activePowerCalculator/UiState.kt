package com.vm.eea.ui.calculators.main.activePowerCalculator

import com.vm.eea.application.SelectableItem


data class UiState(val calculators:List<SelectableItem<ActivePowerInputType>>,
                   val currentInput:ActivePowerInputType,
                   )
{
    companion object{
        fun init() :UiState{
            return UiState(ActivePowerInputType.values().map { SelectableItem(it,false) },
            ActivePowerInputType.ApparentCosPhi)
        }
    }
}