package com.vm.eea.ui.calculators.main.activePowerCalculator.currentImpedance

import com.vm.eea.application.Power
import com.vm.eea.application.PowerSystem
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.CurrentField
import com.vm.eea.ui.ImpedanceField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.activePowerCalculator.ActivePowerInputType

data class CurrentImpedanceState(val current: CurrentField = CurrentField.empty(),
                                 val impedance:  ImpedanceField = ImpedanceField.empty(),
                                 val cosPhi: CosPhiField =CosPhiField.empty(),
                                 val system: PowerSystem = PowerSystem.ThreePhase,
                                 val state: FormState<Power> = FormState.Calculating("")      ){

    val inputType: ActivePowerInputType = ActivePowerInputType.CurrentImpedance
    val filedCount=4
    val progress:Int get() {
        var p=1
        if(current.isValid) p+=1
        if(cosPhi.isValid) p+=1
        if(impedance.isValid) p+=1
        return p

    }

}
