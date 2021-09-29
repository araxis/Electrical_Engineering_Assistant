package com.vm.eea.data.project

import com.vm.eea.application.Conductor
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.project.IGetProjectConductor
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetProjectConductor(private val db:AppDatabase):IGetProjectConductor {
    override suspend fun invoke(projectId: ProjectId): List<SelectableItem<Conductor>> {
       val result=db.projectReadDao().getProjectConductor(projectId.id)
        return Conductor.values().map { SelectableItem(it,isSelected = it==result.value) }
    }
}