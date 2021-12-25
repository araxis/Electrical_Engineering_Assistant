package com.vm.eea.ui.calculators.main.activePowerCalculator.voltageCurrent

import com.vm.eea.application.Power
import com.vm.eea.application.PowerSystem
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.CurrentField
import com.vm.eea.ui.VoltageField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.activePowerCalculator.ActivePowerInputType

data class VoltageCurrentState(
    val current:CurrentField = CurrentField.empty(),
    val voltage:VoltageField = VoltageField.empty(),
    val cosPhi: CosPhiField = CosPhiField.empty(),
    val system: PowerSystem = PowerSystem.ThreePhase,
    val state: FormState<Power> = FormState.Calculating("")
    ) {
    val inputType: ActivePowerInputType = ActivePowerInputType.VoltageCurrent
    val filedCount=4
    val progress:Int get() {
        var p=1
        if(voltage.isValid) p+=1
        if(current.isValid) p+=1
        if(cosPhi.isValid) p+=1
        return p

    }
}