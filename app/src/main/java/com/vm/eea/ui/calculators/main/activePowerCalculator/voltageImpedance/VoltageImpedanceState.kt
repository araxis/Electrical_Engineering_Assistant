package com.vm.eea.ui.calculators.main.activePowerCalculator.voltageImpedance

import com.vm.eea.application.Power
import com.vm.eea.application.PowerSystem
import com.vm.eea.ui.CosPhiField
import com.vm.eea.ui.ImpedanceField
import com.vm.eea.ui.VoltageField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.activePowerCalculator.ActivePowerInputType

data class VoltageImpedanceState(val impedance: ImpedanceField = ImpedanceField.empty(),
                                 val voltage: VoltageField = VoltageField.empty(),
                                 val cosPhi: CosPhiField = CosPhiField.empty(),
                                 val system: PowerSystem = PowerSystem.ThreePhase,
                                 val state: FormState<Power> = FormState.Calculating("")) {

    val inputType: ActivePowerInputType = ActivePowerInputType.VoltageImpedance
    val filedCount=4
    val progress:Int get() {
        var p=1
        if(voltage.isValid) p+=1
        if(impedance.isValid) p+=1
        if(cosPhi.isValid) p+=1
        return p

    }

}