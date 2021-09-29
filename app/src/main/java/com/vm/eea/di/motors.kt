package com.vm.eea.di

import com.vm.eea.application.motor.addMotor.AddMotor
import com.vm.eea.application.motor.updateMotor.*
import com.vm.eea.data.motor.GetMotorDemandFactor
import com.vm.eea.data.motor.GetMotorEfficiency
import com.vm.eea.data.motor.GetMotorPowerfactorDetail
import com.vm.eea.data.motor.GetMotorStartMode
import com.vm.eea.ui.motor.updateMotor.updateMotorStartMode.UpdateStartModeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val motorModule= module {
    factory { AddMotor(get(),get(),get(),get(),get(),get()) }
    factory { UpdateMotorCode(get(),get()) }
    factory { UpdateMotorFeeder(get(),get(),get(),get()) }
    factory { GetMotorPowerfactorDetail(get()) }
    factory{ UpdateMotorPower(get()) }
    factory{ UpdateMotorCosPhi(get()) }
    factory{ UpdateMotorStartMode(get()) }
    factory { UpdateMotorDemandFactor(get(),get()) }
    factory { GetMotorDemandFactor(get()) }
    factory { UpdateMotorEfficiency(get()) }
    factory { GetMotorEfficiency(get()) }
    factory { GetMotorStartMode(get()) }
    viewModel{ params-> UpdateStartModeViewModel(params.get(),get(),get(),get()) }
}