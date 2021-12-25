package com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveCosPhi

import com.vm.eea.application.Power
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.ReactivePowerField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.activePowerCalculator.ActivePowerInputType

data class ReactivePowerCosPhiState(val reactivePowerField: ReactivePowerField= ReactivePowerField.empty(),
                                    val cosPhi: CosPhiField =CosPhiField.empty(),
                                    val state: FormState<Power> = FormState.Calculating("")){

    val inputType: ActivePowerInputType = ActivePowerInputType.ReactiveCosPhi
    val filedCount=2
    val progress:Int get() {
        var p=0
        if(reactivePowerField.isValid) p+=1
        if(cosPhi.isValid) p+=1
        return p

    }



}
