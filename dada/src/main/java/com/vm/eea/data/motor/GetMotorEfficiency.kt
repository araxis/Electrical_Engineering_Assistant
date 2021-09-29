package com.vm.eea.data.motor

import com.vm.eea.application.Efficiency
import com.vm.eea.application.motor.IGetMotorEfficiency
import com.vm.eea.application.motor.MotorId
import com.vm.eea.data.AppDatabase

class GetMotorEfficiency(private val db: AppDatabase):IGetMotorEfficiency {
    override suspend fun invoke(motorId: MotorId): Efficiency {
        return db.motorReadDao().getEfficiency(motorId.id).let { Efficiency(it) }
    }


}