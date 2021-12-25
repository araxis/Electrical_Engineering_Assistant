package com.vm.eea.ui.calculators.main.apparentPowerCalculator.reactiveCosPhi

import com.vm.eea.application.ApparentPower
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.ReactivePowerField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.ApparentPowerInputType

data class UiState(val reactivePowerField: ReactivePowerField= ReactivePowerField.empty(),
                   val cosPhi: CosPhiField =CosPhiField.empty(),
                   val state: FormState<ApparentPower> = FormState.Calculating("")){

    val inputType: ApparentPowerInputType = ApparentPowerInputType.ReactivePowerCosPhi
    val filedCount=2
    val progress:Int get() {
        var p=0
        if(reactivePowerField.isValid) p+=1
        if(cosPhi.isValid) p+=1
        return p

    }



}
