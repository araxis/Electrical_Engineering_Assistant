package com.vm.eea.data.panel

import com.vm.eea.application.Current
import com.vm.eea.application.PanelId
import com.vm.eea.application.Power
import com.vm.eea.application.calculators.applicationProject.panelProject.IGetPanelMotors
import com.vm.eea.application.calculators.applicationProject.panelProject.PanelMotor
import com.vm.eea.application.motor.MotorId
import com.vm.eea.data.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPanelMotors(private val db: AppDatabase):IGetPanelMotors {

    override suspend fun invoke(panelId: PanelId): Flow<List<PanelMotor>> {
       return  db.panelDao().getPanelMotors(panelId.id).map { list->
           list.map {
               PanelMotor(MotorId(it.motorId),Power(it.power,it.powerUnit), Current(it.current,Current.Unit.A))
           }

         }
    }
}