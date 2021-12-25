package com.vm.eea.application.calculators.applicationProject.panelProject

import com.vm.eea.application.Current
import com.vm.eea.application.Power
import com.vm.eea.application.motor.MotorId

data class PanelMotor(val motorId: MotorId,val power:Power, val current: Current)