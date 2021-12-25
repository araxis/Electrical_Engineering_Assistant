package com.vm.eea.di

import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.motor.addMotor.AddMotor
import com.vm.eea.application.motor.addMotor.IGetPanelToMotorDefaultRelationConfig
import com.vm.eea.data.panelToMotorRelation.GetPanelToMotorDefaultRelationConfig
import com.vm.eea.ui.motor.motorFullupdate.UpdateMotorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val motorModule= module {

    factory<IGetPanelToMotorDefaultRelationConfig> { GetPanelToMotorDefaultRelationConfig() }
    factory { AddMotor(get(),get(),get(),get(),get(),get()) }
    viewModel { (motorId:MotorId)-> UpdateMotorViewModel(motorId,get(),get()) }

}