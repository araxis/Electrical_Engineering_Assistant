package com.vm.eea.ui.calculators.main.apparentPowerCalculator

import com.vm.eea.application.SelectableItem

data class State(val calculators:List<SelectableItem<ApparentPowerInputType>>,
                 val currentInput: ApparentPowerInputType){
    companion object{
        fun init() : State {
            return State(ApparentPowerInputType.values().map { SelectableItem(it,false) },
                ApparentPowerInputType.VoltageCurrent)
        }
    }
}
