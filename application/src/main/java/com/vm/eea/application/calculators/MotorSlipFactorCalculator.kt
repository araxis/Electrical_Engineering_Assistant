package com.vm.eea.application.calculators

import com.vm.eea.application.SlipFactor
import com.vm.eea.application.Speed

class MotorSlipFactorCalculator {
    operator fun invoke(syncSpeed:Speed, rotorSpeed:Speed):SlipFactor{
        if(rotorSpeed > syncSpeed) return SlipFactor(0)
        val ret=(syncSpeed-rotorSpeed)/syncSpeed
        return SlipFactor(ret.value*100)
    }
}