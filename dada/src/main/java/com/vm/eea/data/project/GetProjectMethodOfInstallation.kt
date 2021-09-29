package com.vm.eea.data.project

import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.project.IGetProjectMethodOfInstallation
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetProjectMethodOfInstallation(private val db:AppDatabase):IGetProjectMethodOfInstallation {
    override suspend fun invoke(projectId: ProjectId): List<SelectableItem<MethodOfInstallation>> {
        val value=db.projectReadDao().getProjectMethodOfInstallation(projectId.id)
        return MethodOfInstallation.values().map {
            SelectableItem(it,isSelected = it==value)
        }
    }
}