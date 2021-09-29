package com.vm.eea.application.project


import com.vm.eea.application.Current
import kotlinx.coroutines.flow.Flow

interface IGetProjectSimpleList {
    operator fun invoke(): Flow<List<SimpleProject>>

    data class SimpleProject(val id: ProjectId, val code:String, val description:String, val current: Current)
}

