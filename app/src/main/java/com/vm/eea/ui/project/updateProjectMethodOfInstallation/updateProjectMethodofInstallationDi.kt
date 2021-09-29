package com.vm.eea.ui.project.updateProjectMethodOfInstallation

import com.vm.eea.application.project.IGetProjectMethodOfInstallation
import com.vm.eea.application.project.update.UpdateProjectMethodOfInstallation
import com.vm.eea.data.project.GetProjectMethodOfInstallation
import org.koin.dsl.module

val updateProjectMethodOfInstallationModule= module {
    factory<IGetProjectMethodOfInstallation> { GetProjectMethodOfInstallation(get()) }
    factory { UpdateProjectMethodOfInstallation(get()) }
}