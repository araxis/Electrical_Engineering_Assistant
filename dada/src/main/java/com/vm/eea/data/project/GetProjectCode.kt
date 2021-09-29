package com.vm.eea.data.project

import com.vm.eea.application.project.IGetProjectCode
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProjectCode(private val db:AppDatabase):IGetProjectCode {
    override suspend fun invoke(projectId: ProjectId): Flow<IGetProjectCode.Result> {
        return db.projectReadDao().getProjectCode(projectId.id)
            .map { IGetProjectCode.Result(ProjectId(it.id),it.code,it.description) }
    }
}