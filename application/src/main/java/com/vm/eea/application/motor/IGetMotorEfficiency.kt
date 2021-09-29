package com.vm.eea.application.motor

import com.vm.eea.application.Efficiency

interface IGetMotorEfficiency {

    suspend operator fun invoke(motorId:MotorId):Efficiency
}