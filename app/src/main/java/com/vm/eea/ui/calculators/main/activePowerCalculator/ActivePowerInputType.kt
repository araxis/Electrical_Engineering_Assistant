package com.vm.eea.ui.calculators.main.activePowerCalculator

import com.vm.eea.utilities.CosPhi

interface  IActivePowerInputType {
    val description:String
}

enum class ActivePowerInputType:IActivePowerInputType{
    ApparentCosPhi {override val description ="Apparent input , $CosPhi "},
    CurrentImpedance{override val description ="Current , Impedance"},
    CurrentResistance{override val description ="Current , Resistance"},
    ReactiveApparent{override val description ="Reactive input , Apparent input"},
    ReactiveCosPhi{override val description ="Reactive input , $CosPhi"},
    VoltageCurrent{override val description ="Voltage , Current"},
    VoltageImpedance{override val description ="Voltage , Impedance"},
    ;

}