package com.vm.eea.application.motor

import com.vm.eea.application.CosPhi

interface IGetMotorDemandFactor {

    suspend operator fun invoke(motorId: MotorId):CosPhi
}