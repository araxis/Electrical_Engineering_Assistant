package com.vm.eea.ui.calculators.main.currentCalculator

import com.vm.eea.application.Current
import com.vm.eea.application.PowerSystem
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.EfficiencyField
import com.vm.eea.ui.PowerField
import com.vm.eea.ui.VoltageField
import com.vm.eea.ui.calculators.FormState

data class UiState(
    val power: PowerField = PowerField.empty(),
    val voltage: VoltageField = VoltageField.empty(),
    val cosPhi: CosPhiField = CosPhiField.empty(),
    val system: PowerSystem=PowerSystem.ThreePhase,
    val efficiency:EfficiencyField= EfficiencyField.empty(),
    val state: FormState<Current> =FormState.Calculating("waiting for input (1/5)")
){
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