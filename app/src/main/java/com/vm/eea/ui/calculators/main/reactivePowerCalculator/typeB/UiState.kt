package com.vm.eea.ui.calculators.main.reactivePowerCalculator.typeB

import com.vm.eea.application.ReactivePower
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.PowerField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.ApparentPowerInputType

data class UiState(val power: PowerField = PowerField.empty(),
                   val cosPhi: CosPhiField = CosPhiField.empty(),
                   val state: FormState<ReactivePower> = FormState.Calculating("")){

    val inputType: ApparentPowerInputType = ApparentPowerInputType.VoltageCurrent
    val filedCount=2
    val progress:Int get() {
        var p=0
        if(power.isValid) p+=1
        if(cosPhi.isValid) p+=1
        return p

    }
}
