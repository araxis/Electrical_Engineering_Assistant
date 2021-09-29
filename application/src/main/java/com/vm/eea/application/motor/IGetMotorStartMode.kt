package com.vm.eea.application.motor

import com.vm.eea.application.SelectableItem
import com.vm.eea.application.StartMode

interface IGetMotorStartMode {

    suspend operator fun invoke(motorId: MotorId):List<SelectableItem<StartMode>>
}