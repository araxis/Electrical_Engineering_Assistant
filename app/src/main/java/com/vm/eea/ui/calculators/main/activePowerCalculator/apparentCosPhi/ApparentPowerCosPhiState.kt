package com.vm.eea.ui.calculators.main.activePowerCalculator.apparentCosPhi

import com.vm.eea.application.Power
import com.vm.eea.ui.ApparentPowerField
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.activePowerCalculator.ActivePowerInputType

data class ApparentPowerCosPhiState(
    val apparentPower: ApparentPowerField = ApparentPowerField.empty(),
    val cosPhi: CosPhiField = CosPhiField.empty(),
    val state: FormState<Power> = FormState.Calculating(""),
){
    val inputType:ActivePowerInputType=ActivePowerInputType.ApparentCosPhi
    val filedCount=2
    val progress:Int get() {
        var p=0
        if(apparentPower.isValid) p+=1
        if(cosPhi.isValid) p+=1
        return p

    }



}
