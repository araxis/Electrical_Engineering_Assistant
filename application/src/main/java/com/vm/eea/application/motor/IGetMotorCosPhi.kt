package com.vm.eea.application.motor

import com.vm.eea.application.CosPhi

interface IGetMotorCosPhi {

    suspend operator fun invoke(motorId: MotorId):CosPhi
}