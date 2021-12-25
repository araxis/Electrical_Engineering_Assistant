package com.vm.eea.ui.calculators.motor.voltage

import com.vm.eea.application.PowerSystem
import com.vm.eea.application.Voltage
import com.vm.eea.ui.*
import com.vm.eea.ui.calculators.FormState

data class UiState(
    val power: PowerField = PowerField.empty(),
    val current: CurrentField = CurrentField.empty(),
    val cosPhi: CosPhiField = CosPhiField.empty(),
    val system: PowerSystemField = PowerSystemField.empty(),
    val efficiency: EfficiencyField = EfficiencyField.empty(),
    val state: FormState<Voltage> = FormState.Calculating("waiting for input (1/5)")
){
    val filedCount=5
    val progress:Int get() {
        var p=1
        if(current.isValid) p+=1
        if(power.isValid) p+=1
        if(cosPhi.isValid) p+=1
        if(efficiency.isValid) p+=1
        return p

    }

}