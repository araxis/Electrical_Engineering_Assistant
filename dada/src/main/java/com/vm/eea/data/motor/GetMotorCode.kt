package com.vm.eea.data.motor

import com.vm.eea.application.motor.IGetMotorCode
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.motor.SimpleMotor
import com.vm.eea.data.AppDatabase

class GetMotorCode(private val db:AppDatabase): IGetMotorCode {
    override suspend fun invoke(motorId: MotorId): SimpleMotor {
        return db.motorReadDao().getSimpleInfo(motorId.id)
            .let { SimpleMotor(MotorId(it.id),it.code,it.description) }
    }
}