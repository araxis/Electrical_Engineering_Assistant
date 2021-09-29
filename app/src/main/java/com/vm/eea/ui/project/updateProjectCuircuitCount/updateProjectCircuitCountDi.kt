package com.vm.eea.ui.project.updateProjectCuircuitCount

import com.vm.eea.application.project.IGetProjectCircuitCount
import com.vm.eea.application.project.update.UpdateProjectCircuitCount
import com.vm.eea.data.project.GetProjectCircuitCount
import org.koin.dsl.module

val updateProjectCircuitCountModule= module {
    factory<IGetProjectCircuitCount> { GetProjectCircuitCount(get()) }
    factory { UpdateProjectCircuitCount(get()) }
}