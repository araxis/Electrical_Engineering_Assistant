package com.vm.eea.di


import com.vm.eea.application.calculators.applicationProject.motorProject.AddMotorApplicationProject
import com.vm.eea.application.calculators.applicationProject.motorProject.ApplicationMotorUpdater
import com.vm.eea.application.calculators.applicationProject.motorProject.IApplicationProjectCodeResolver
import com.vm.eea.application.calculators.applicationProject.motorProject.IGetApplicationMotor
import com.vm.eea.application.calculators.applicationProject.panelProject.AddPanelApplicationProject
import com.vm.eea.application.calculators.applicationProject.panelProject.IGetApplicationPanel
import com.vm.eea.application.calculators.applicationProject.panelProject.IGetPanelMotors
import com.vm.eea.application.project.IGetProjectDefaults
import com.vm.eea.application.project.NewProjectFactory
import com.vm.eea.data.motor.GetApplicationMotor
import com.vm.eea.data.panel.GetApplicationPanel
import com.vm.eea.data.panel.GetPanelMotors
import com.vm.eea.data.project.GetProjectDefaults
import com.vm.eea.application.motor.addMotor.IMotorUniqueCodeGenerator
import org.koin.dsl.module
import java.util.*

val projectModule= module {
    factory<IGetApplicationMotor> { GetApplicationMotor(get(),get()) }
    factory<IGetApplicationPanel> { GetApplicationPanel(get(),get()) }
    factory<IGetPanelMotors> { GetPanelMotors(get()) }
    single<IApplicationProjectCodeResolver> { object : IApplicationProjectCodeResolver {
        override  fun motorProjectCode()="motorApp_pr_code_1360_12_05"
        override  fun panelProjectCode()="panelApp_pr_code_1360_12_05"

    } }
    single<IMotorUniqueCodeGenerator> { object : IMotorUniqueCodeGenerator {
        override fun invoke() = UUID.randomUUID().toString()

    } }
    factory { ApplicationMotorUpdater(get(),get(),get()) }
    factory { AddMotorApplicationProject(get(),get(),get(),get(),get(),get(),) }
    factory { AddPanelApplicationProject(get(),get(),get(),get(),get()) }

    factory<IGetProjectDefaults> {GetProjectDefaults()  }
    factory { NewProjectFactory(get()) }








}