package com.vm.eea.ui.calculators.main.reactivePowerCalculator.typeD

import com.vm.eea.application.ReactivePower
import com.vm.eea.ui.ApparentPowerField
import com.vm.eea.ui.PowerField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.ApparentPowerInputType

data class UiState(val activePower: PowerField= PowerField.empty(),
                   val apparentPower: ApparentPowerField = ApparentPowerField.empty(),
                   val state: FormState<ReactivePower> = FormState.Calculating("")){

    val inputType: ApparentPowerInputType = ApparentPowerInputType.ActiveReactive
    val filedCount=2
    val progress:Int get() {
        var p=0
        if(activePower.isValid) p+=1
        if(apparentPower.isValid) p+=1
        return p

    }



}
