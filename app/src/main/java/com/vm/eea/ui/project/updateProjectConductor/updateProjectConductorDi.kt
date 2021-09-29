package com.vm.eea.ui.project.updateProjectConductor

import com.vm.eea.application.project.IGetProjectConductor
import com.vm.eea.application.project.update.UpdateProjectConductor
import com.vm.eea.data.project.GetProjectConductor
import org.koin.dsl.module

val updateProjectConductorModule= module {
    factory<IGetProjectConductor> { GetProjectConductor(get()) }
    factory { UpdateProjectConductor(get()) }
}