package com.vm.eea.ui.project.updateProjectInsulation

import com.vm.eea.application.project.IGetProjectInsulation
import com.vm.eea.application.project.update.UpdateProjectInsulation
import com.vm.eea.data.project.GetProjectInsulation
import org.koin.dsl.module

val updateProjectInsulationModule= module {
    factory<IGetProjectInsulation> { GetProjectInsulation(get()) }
    factory{ UpdateProjectInsulation(get()) }
}