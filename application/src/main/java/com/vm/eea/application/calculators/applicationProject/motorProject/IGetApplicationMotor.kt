package com.vm.eea.application.calculators.applicationProject.motorProject

import com.vm.eea.application.motor.MotorId

interface IGetApplicationMotor {

    suspend operator fun invoke(): ApplicationMotor
    suspend operator fun invoke(motorId:MotorId): ApplicationMotor
}