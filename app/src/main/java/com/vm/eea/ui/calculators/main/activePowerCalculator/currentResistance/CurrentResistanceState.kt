package com.vm.eea.ui.calculators.main.activePowerCalculator.currentResistance

import com.vm.eea.application.Power
import com.vm.eea.application.PowerSystem
import com.vm.eea.ui.CurrentField
import com.vm.eea.ui.ResistanceField
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.activePowerCalculator.ActivePowerInputType

data class CurrentResistanceState(val current: CurrentField = CurrentField.empty(),
                                  val resistance: ResistanceField = ResistanceField.empty(),
                                  val system: PowerSystem = PowerSystem.ThreePhase,
                                  val state: FormState<Power> = FormState.Calculating("")){

    val inputType: ActivePowerInputType = ActivePowerInputType.CurrentResistance
    val filedCount=3
    val progress:Int get() {
        var p=1
        if(current.isValid) p+=1
        if(resistance.isValid) p+=1
        return p

    }

}
