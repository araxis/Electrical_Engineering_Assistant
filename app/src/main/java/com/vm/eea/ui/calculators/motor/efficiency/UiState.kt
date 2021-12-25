package com.vm.eea.ui.calculators.motor.efficiency

import com.vm.eea.application.Efficiency
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.calculators.EfficiencyResult
import com.vm.eea.ui.*
import com.vm.eea.ui.calculators.FormState

data class UiState(
    val power: PowerField = PowerField.empty(),
    val current: CurrentField = CurrentField.empty(),
    val voltage: WorkingVoltageField = WorkingVoltageField.empty(),
    val cosPhi: CosPhiField = CosPhiField.empty(),
    val state: FormState<EfficiencyResult> = FormState.Calculating("")
){
    val filedCount=4
    val progress:Int get() {
        var p=0
        if(current.isValid) p+=1
        if(power.isValid) p+=1
        if(voltage.isValid) p+=1
        if(cosPhi.isValid) p+=1
        return p

    }

}