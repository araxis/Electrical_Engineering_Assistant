package com.vm.eea.data.motor

import com.vm.eea.application.SelectableItem
import com.vm.eea.application.StartMode
import com.vm.eea.application.motor.IGetMotorStartMode
import com.vm.eea.application.motor.MotorId
import com.vm.eea.data.AppDatabase

class GetMotorStartMode(private val db:AppDatabase): IGetMotorStartMode {
    override suspend fun invoke(motorId: MotorId): List<SelectableItem<StartMode>> {
        val current:StartMode =db.motorReadDao().getStartMode(motorId.id)
        return StartMode.values().map { SelectableItem(it,isSelected = it==current) }
    }
}