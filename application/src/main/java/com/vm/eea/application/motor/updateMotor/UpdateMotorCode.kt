package com.vm.eea.application.motor.updateMotor

import com.vm.eea.application.motor.IMotorCodeExistChecker
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.MotorCode
import com.vm.eea.application.motor.MotorId

class UpdateMotorCode(private val motorCodeExistChecker: IMotorCodeExistChecker,
                      private val repository: IMotorRepository ) {

    suspend operator fun invoke(loadId: MotorId, code: String, description: String){
        val motorCode=MotorCode(code)
        val isExist=motorCodeExistChecker(motorCode)
        require(!isExist){"Duplicated Code"}
        repository.updateCode(loadId,motorCode,description)
    }
}