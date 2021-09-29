package com.vm.eea.di

import com.vm.eea.data.project.GetProjectDetails
import org.koin.dsl.module

val readModule= module {
    //factory<IProjectReadRepository> { ProjectReadRepository(get())  }
    factory { GetProjectDetails(get()) }
}