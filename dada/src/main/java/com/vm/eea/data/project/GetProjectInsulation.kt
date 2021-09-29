package com.vm.eea.data.project

import com.vm.eea.application.Insulation
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.project.IGetProjectInsulation
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetProjectInsulation(private val db:AppDatabase):IGetProjectInsulation {
    override suspend fun invoke(projectId: ProjectId): List<SelectableItem<Insulation>> {
        val insulation=db.projectReadDao().getProjectInsulation(projectId.id)
        return Insulation.values().map { SelectableItem(it,isSelected = it==insulation) }
    }
}