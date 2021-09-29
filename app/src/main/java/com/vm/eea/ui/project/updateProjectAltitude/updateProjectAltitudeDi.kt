package com.vm.eea.ui.project.updateProjectAltitude

import com.vm.eea.application.project.IGetProjectAltitude
import com.vm.eea.data.project.GetProjectAltitude
import com.vm.eea.application.project.update.UpdateProjectAltitude
import org.koin.dsl.module

val updateProjectCodeModule= module {
    factory<IGetProjectAltitude> { GetProjectAltitude(get()) }
    factory { UpdateProjectAltitude(get()) }
}