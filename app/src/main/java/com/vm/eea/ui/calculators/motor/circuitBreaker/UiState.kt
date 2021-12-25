package com.vm.eea.ui.calculators.motor.circuitBreaker

import com.vm.eea.application.protectionDevice.ProtectionDeviceResult
import com.vm.eea.ui.*
import com.vm.eea.ui.calculators.FormState

data class UiState(val power: PowerField = PowerField.empty(),
                   val voltage: WorkingVoltageField = WorkingVoltageField.empty(),
                   val protectionType:ProtectionTypeField = ProtectionTypeField.empty("Protection device"),
                   val cosPhi: CosPhiField = CosPhiField.empty(),
                   val efficiency: EfficiencyField = EfficiencyField.empty(),
                   val state: FormState<ProtectionDeviceResult> = FormState.Calculating("")){
    val filedCount=5
    val progress:Int get() {
        var p=1
        if(voltage.isValid) p+=1
        if(power.isValid) p+=1
        if(cosPhi.isValid) p+=1
        if(efficiency.isValid) p+=1
        return p

    }

}
