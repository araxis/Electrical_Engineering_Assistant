package com.vm.eea.ui.project.updateProjectCode

import com.vm.eea.application.project.IGetProjectCode
import com.vm.eea.application.project.update.UpdateProjectCode
import com.vm.eea.data.project.GetProjectCode
import org.koin.dsl.module

val updateProjectCodeModule= module {
    factory<IGetProjectCode> { GetProjectCode(get()) }
    factory{ UpdateProjectCode(get()) }
}