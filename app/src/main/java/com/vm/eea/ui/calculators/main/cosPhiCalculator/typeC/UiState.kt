package com.vm.eea.ui.calculators.main.cosPhiCalculator.typeC

import com.vm.eea.application.CosPhi
import com.vm.eea.ui.ApparentPowerField
import com.vm.eea.ui.ReactivePowerField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.activePowerCalculator.ActivePowerInputType

data class UiState(val reactivePowerField: ReactivePowerField = ReactivePowerField.empty(),
                                 val apparentPower: ApparentPowerField = ApparentPowerField.empty(),
                                 val state: FormState<CosPhi> = FormState.Calculating("")){

    val inputType: ActivePowerInputType = ActivePowerInputType.ReactiveApparent
    val filedCount=2
    val progress:Int get() {
        var p=0
        if(reactivePowerField.isValid) p+=1
        if(apparentPower.isValid) p+=1
        return p

    }



}