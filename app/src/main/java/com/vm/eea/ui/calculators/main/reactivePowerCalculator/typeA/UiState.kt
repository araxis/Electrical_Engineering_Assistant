package com.vm.eea.ui.calculators.main.reactivePowerCalculator.typeA

import com.vm.eea.application.PowerSystem
import com.vm.eea.application.ReactivePower
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.CurrentField
import com.vm.eea.ui.VoltageField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.ApparentPowerInputType

data class UiState(
    val current: CurrentField = CurrentField.empty(),
    val voltage: VoltageField = VoltageField.empty(),
    val cosPhi: CosPhiField = CosPhiField.empty(),
    val system: PowerSystem = PowerSystem.ThreePhase,
    val state: FormState<ReactivePower> = FormState.Calculating("")
) {
    val inputType: ApparentPowerInputType = ApparentPowerInputType.VoltageCurrent
    val filedCount=4
    val progress:Int get() {
        var p=1
        if(voltage.isValid) p+=1
        if(current.isValid) p+=1
        return p

    }
}
