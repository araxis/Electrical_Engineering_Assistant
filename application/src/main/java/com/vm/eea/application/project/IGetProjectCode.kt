package com.vm.eea.application.project

import kotlinx.coroutines.flow.Flow

interface IGetProjectCode {
    suspend operator fun invoke(projectId: ProjectId): Flow<Result>

    data class Result(val projectId: ProjectId,val code:String, val description:String)
}