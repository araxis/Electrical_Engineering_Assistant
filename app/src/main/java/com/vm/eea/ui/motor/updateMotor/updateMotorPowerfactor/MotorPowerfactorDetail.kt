package com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor

import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.load.LoadId

data class MotorPowerfactorDetail(val motorId:LoadId, val code:String, val powerfactor:PowerFactor)