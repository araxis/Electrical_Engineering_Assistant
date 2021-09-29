package com.vm.eea.application.calculators

import com.vm.eea.application.CosPhi
import com.vm.eea.application.Power
import com.vm.eea.application.ReactivePower

class RequiredVarCalculator {

    operator fun invoke(power: Power, cosPhi: CosPhi, demandFactor:CosPhi): ReactivePower {
        val basePower=(power to Power.Unit.W).value
        val ret=basePower * (cosPhi.tanPhi()-demandFactor.tanPhi())
        return ReactivePower(ret,ReactivePower.Unit.VAr)
    }

}