package com.vm.eea.ui.calculators.main.apparentPowerCalculator

import com.vm.eea.utilities.CosPhi

interface IApparentPowerInputType {
    val description:String
}

enum class ApparentPowerInputType:IApparentPowerInputType{
    VoltageCurrent{override val description="Voltage , Current"},
    PowerCosPhi{override val description="Active input , $CosPhi"},
    ReactivePowerCosPhi{override val description="Reactive input , $CosPhi"},
    ActiveReactive{override val description="Active input , Reactive input"}
    ;
}