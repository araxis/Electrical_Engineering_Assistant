package com.vm.eea.application.motor

import com.vm.eea.application.Power

interface IGetMotorPower {

    suspend operator fun invoke(motorId: MotorId):Power
}