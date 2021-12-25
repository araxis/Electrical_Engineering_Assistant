package com.vm.eea.application.calculators

import com.vm.eea.application.Speed

class MotorSlipSpeedCalculator {
    operator fun invoke(syncSpeed:Speed, rotorSpeed:Speed):Speed{
        return syncSpeed-rotorSpeed

    }
}