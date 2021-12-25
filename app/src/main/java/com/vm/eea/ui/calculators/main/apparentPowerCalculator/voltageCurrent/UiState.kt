package com.vm.eea.ui.calculators.main.apparentPowerCalculator.voltageCurrent

import com.vm.eea.application.ApparentPower
import com.vm.eea.application.PowerSystem
import com.vm.eea.ui.CurrentField
import com.vm.eea.ui.VoltageField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.ApparentPowerInputType

data class UiState(
    val current:CurrentField = CurrentField.empty(),
    val voltage:VoltageField = VoltageField.empty(),
    val system: PowerSystem = PowerSystem.ThreePhase,
    val state: FormState<ApparentPower> = FormState.Calculating("")
    ) {
    val inputType: ApparentPowerInputType = ApparentPowerInputType.VoltageCurrent
    val filedCount=3
    val progress:Int get() {
        var p=1
        if(voltage.isValid) p+=1
        if(current.isValid) p+=1
        return p

    }
}