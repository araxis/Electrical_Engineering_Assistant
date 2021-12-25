package com.vm.eea.ui.calculators.motor.torque

import com.vm.eea.application.Torque
import com.vm.eea.ui.PowerField
import com.vm.eea.ui.SpeedField
import com.vm.eea.ui.calculators.FormState

data class UiState(val power:PowerField = PowerField.empty(),
                   val speed: SpeedField = SpeedField.empty(""),
                   val state:FormState<Torque> =FormState.Calculating("")){
    val filedCount=2
    val progress:Int get() {
        var p=0
        if(power.isValid) p+=1
        if(speed.isValid) p+=1
        return p

    }
}
