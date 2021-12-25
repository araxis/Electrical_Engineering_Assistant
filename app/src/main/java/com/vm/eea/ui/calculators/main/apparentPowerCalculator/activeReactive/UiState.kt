package com.vm.eea.ui.calculators.main.apparentPowerCalculator.activeReactive

import com.vm.eea.application.ApparentPower
import com.vm.eea.ui.PowerField
import com.vm.eea.ui.ReactivePowerField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.ApparentPowerInputType

data class UiState(val activePower: PowerField= PowerField.empty(),
                   val reactivePower: ReactivePowerField = ReactivePowerField.empty(),
                   val state: FormState<ApparentPower> = FormState.Calculating("")){

    val inputType: ApparentPowerInputType = ApparentPowerInputType.ActiveReactive
    val filedCount=2
    val progress:Int get() {
        var p=0
        if(activePower.isValid) p+=1
        if(reactivePower.isValid) p+=1
        return p

    }



}
