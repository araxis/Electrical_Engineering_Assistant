package com.vm.eea.application.motor

interface IMotorCodeExistChecker {

    suspend operator fun invoke(motorCode: MotorCode):Boolean
}