package com.vm.eea.application.calculators

import com.vm.eea.application.KW
import com.vm.eea.application.Power
import com.vm.eea.application.StartMode

class StartModeCalculator {

    operator fun invoke(power: Power): StartMode {
        if (power < 15.KW) return StartMode.Dol
        return if (power < 132.KW)  StartMode.Sd else StartMode.SSd
    }
}