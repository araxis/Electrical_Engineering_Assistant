package com.vm.eea.ui.project.updateProjectTemperature

import com.vm.eea.application.project.IGetProjectTemperature
import com.vm.eea.application.project.update.UpdateProjectTemperature
import com.vm.eea.data.project.GetProjectTemperature
import org.koin.dsl.module

val updateProjectTemperatureModule= module {
    factory<IGetProjectTemperature> { GetProjectTemperature(get()) }
    factory { UpdateProjectTemperature(get()) }
}