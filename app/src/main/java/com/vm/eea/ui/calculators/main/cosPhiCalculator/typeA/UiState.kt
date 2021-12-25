package com.vm.eea.ui.calculators.main.cosPhiCalculator.typeA

import com.vm.eea.application.PowerSystem
import com.vm.eea.application.calculators.CosPhiResult
import com.vm.eea.ui.CurrentField
import com.vm.eea.ui.PowerField
import com.vm.eea.ui.VoltageField
import com.vm.eea.ui.calculators.FormState

data class UiState(
    val power: PowerField = PowerField.empty(),
    val voltage: VoltageField = VoltageField.empty(),
    val current: CurrentField = CurrentField.empty(),
    val system: PowerSystem =PowerSystem.ThreePhase,
    val state: FormState<CosPhiResult> = FormState.Calculating("waiting for input (1/5)"),
) {

    val filedCount=4
    val progress:Int get() {
        var p=1
        if(voltage.isValid) p+=1
        if(power.isValid) p+=1
        if(current.isValid) p+=1
        return p

    }
}