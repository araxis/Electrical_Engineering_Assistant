package com.vm.eea.ui.calculators.motor.current

import com.vm.eea.application.Current
import com.vm.eea.application.PowerSystem
import com.vm.eea.ui.*
import com.vm.eea.ui.calculators.FormState

data class UiState(
    val power: PowerField = PowerField.empty(),
    val voltage: WorkingVoltageField = WorkingVoltageField.empty(),
    val cosPhi: CosPhiField = CosPhiField.empty(),
    val efficiency: EfficiencyField = EfficiencyField.empty(),
    val state: FormState<Current> = FormState.Calculating("waiting for input (1/4)")
){
    val filedCount=4
    val progress:Int get() {
        var p=0
        if(voltage.isValid) p+=1
        if(power.isValid) p+=1
        if(cosPhi.isValid) p+=1
        if(efficiency.isValid) p+=1
        return p

    }

}