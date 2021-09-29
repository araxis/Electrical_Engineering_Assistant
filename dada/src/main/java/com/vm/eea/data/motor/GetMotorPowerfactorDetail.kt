package com.vm.eea.data.motor

import com.vm.eea.application.CosPhi
import com.vm.eea.application.motor.IGetMotorCosPhi
import com.vm.eea.application.motor.MotorId
import com.vm.eea.data.AppDatabase

class GetMotorPowerfactorDetail(private val db:AppDatabase):IGetMotorCosPhi {
    override suspend fun invoke(motorId: MotorId): CosPhi {
        TODO("Not yet implemented")
    }


}