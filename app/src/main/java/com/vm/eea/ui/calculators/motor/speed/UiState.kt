package com.vm.eea.ui.calculators.motor.speed

import com.vm.eea.application.HZ
import com.vm.eea.ui.FrequencyField
import com.vm.eea.ui.SlipFactorField
import com.vm.eea.ui.StringField
import com.vm.eea.ui.calculators.FormState

data class UiState(
    val numberOfPoles: StringField = StringField.empty(),
    val frequency: FrequencyField = FrequencyField.valid(50.HZ),
    val slipFactor: SlipFactorField = SlipFactorField.empty(),
    val state: FormState<SpeedResult> =FormState.Calculating("")
) {

    val filedCount=3
    val progress:Int get() {
        var p=0
        if(numberOfPoles.isValid) p+=1
        if(frequency.isValid) p+=1
        if(slipFactor.isValid) p+=1
        return p

    }

}

