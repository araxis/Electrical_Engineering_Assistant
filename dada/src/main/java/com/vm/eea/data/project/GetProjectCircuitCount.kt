package com.vm.eea.data.project

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.project.IGetProjectCircuitCount
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetProjectCircuitCount(private val db:AppDatabase):IGetProjectCircuitCount {
    override suspend fun invoke(projectId: ProjectId): List<SelectableItem<CircuitCount>> {
        val value=db.projectReadDao().getProjectCircuitCount(projectId.id)
        val defaultsCircuitCounts=  listOf(1,2,3,4,5,6,7,8,9,12,16,20)
        return defaultsCircuitCounts.map { SelectableItem(CircuitCount(it),isSelected = it==value) }
    }
}