package com.vm.eea.ui.project.updateProjectVoltage

import com.vm.eea.application.project.IGetProjectVoltage
import com.vm.eea.application.project.update.UpdateProjectVoltage
import com.vm.eea.data.project.GetProjectVoltage
import org.koin.dsl.module

val updateProjectTemperatureModule= module {
    factory<IGetProjectVoltage> { GetProjectVoltage(get()) }
    factory { UpdateProjectVoltage(get()) }
}