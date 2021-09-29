package com.vm.eea.ui.project.updateProjectMaxvoltDrop

import com.vm.eea.application.project.IGetProjectMaxVoltDrop
import com.vm.eea.application.project.update.UpdateProjectMaxVoltDrop
import com.vm.eea.data.project.GetProjectMaxVoltDrop
import org.koin.dsl.module

val updateProjectMaxVoltDropModule= module {
    factory<IGetProjectMaxVoltDrop> { GetProjectMaxVoltDrop(get()) }
    factory { UpdateProjectMaxVoltDrop(get()) }
}