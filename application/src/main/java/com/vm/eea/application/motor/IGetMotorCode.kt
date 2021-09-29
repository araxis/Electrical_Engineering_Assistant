package com.vm.eea.application.motor

interface IGetMotorCode {
    suspend operator fun invoke(motorId: MotorId):SimpleMotor
}