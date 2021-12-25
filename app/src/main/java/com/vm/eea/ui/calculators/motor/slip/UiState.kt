package com.vm.eea.ui.calculators.motor.slip

import com.vm.eea.ui.SpeedField
import com.vm.eea.ui.calculators.FormState

data class UiState(
    val syncSpeed: SpeedField = SpeedField.empty("Synchronous speed"),
    val rotorSpeed: SpeedField = SpeedField.empty("Rotor speed"),
    val state: FormState<SlipResult> = FormState.Calculating(""),
){
    val filedCount=2
    val progress:Int get() {
        var p=0
        if(syncSpeed.isValid) p+=1
        if(rotorSpeed.isValid) p+=1
        return p

    }
}
