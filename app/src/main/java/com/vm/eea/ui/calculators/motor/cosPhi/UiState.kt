package com.vm.eea.ui.calculators.motor.cosPhi

import com.vm.eea.application.CosPhi
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.calculators.CosPhiResult
import com.vm.eea.ui.*
import com.vm.eea.ui.calculators.FormState

data class UiState(
    val power: PowerField = PowerField.empty(),
    val current: CurrentField = CurrentField.empty(),
    val voltage: WorkingVoltageField = WorkingVoltageField.empty(),
    val efficiency: EfficiencyField = EfficiencyField.empty(),
    val state: FormState<CosPhiResult> = FormState.Calculating("")
){
    val filedCount=4
    val progress:Int get() {
        var p=0
        if(current.isValid) p+=1
        if(power.isValid) p+=1
        if(voltage.isValid) p+=1
        if(efficiency.isValid) p+=1
        return p

    }

}