package com.vm.eea.ui.calculators.motor.guard

import com.vm.eea.application.StartMode
import com.vm.eea.application.bimetal.BimetalResult
import com.vm.eea.ui.*
import com.vm.eea.ui.calculators.FormState

data class UiState(val power: PowerField = PowerField.empty(),
                   val voltage: WorkingVoltageField = WorkingVoltageField.empty(),
                   val cosPhi: CosPhiField = CosPhiField.empty(),
                   val startMode:StartModeField =StartModeField.empty("Start mode",StartMode.Dol),
                   val keyType:KeyTypeField = KeyTypeField.empty("Circuit breaker"),
                   val efficiency: EfficiencyField = EfficiencyField.empty(),
                   val state: FormState<BimetalResult> = FormState.Calculating("")){
    val filedCount=6
    val progress:Int get() {
        var p=1
        if(voltage.isValid) p+=1
        if(keyType.isValid) p+=1
        if(power.isValid) p+=1
        if(cosPhi.isValid) p+=1
        if(efficiency.isValid) p+=1
        return p

    }

}
