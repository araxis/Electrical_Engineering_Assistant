package com.vm.eea.application.motor

import com.vm.eea.application.SelectableItem

interface IGetMotorFeeder {

    suspend operator fun invoke(motorId: MotorId):List<SelectableItem<MotorFeeder>>

}

