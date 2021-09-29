package com.vm.eea.data.project

import com.vm.eea.application.Length
import com.vm.eea.application.project.IGetProjectAltitude
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetProjectAltitude(private val db:AppDatabase):IGetProjectAltitude {
    override suspend fun invoke(projectId: ProjectId): Length {
        return db.projectReadDao().getProjectAltitude(projectId.id).let {Length(it.value,it.unit)}

    }
}