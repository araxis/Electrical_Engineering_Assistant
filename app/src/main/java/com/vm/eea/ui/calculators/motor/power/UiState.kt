package com.vm.eea.ui.calculators.motor.power

import com.vm.eea.application.Power
import com.vm.eea.application.PowerSystem
import com.vm.eea.ui.*
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.activePowerCalculator.ActivePowerInputType

data class UiState(
    val current: CurrentField = CurrentField.empty(),
    val voltage: WorkingVoltageField = WorkingVoltageField.empty(),
    val cosPhi: CosPhiField = CosPhiField.empty(),
    val efficiency:EfficiencyField = EfficiencyField.empty(),
    val state: FormState<Power> = FormState.Calculating("")
) {
    val filedCount=4
    val progress:Int get() {
        var p=0
        if(voltage.isValid) p+=1
        if(efficiency.isValid) p+=1
        if(current.isValid) p+=1
        if(cosPhi.isValid) p+=1
        return p

    }
}