package com.vm.eea.data.project

import com.vm.eea.application.Current
import com.vm.eea.application.PanelId
import com.vm.eea.application.project.IGetProjectPanels
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.ProjectPanelsResult
import com.vm.eea.data.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProjectPanels(private val db:AppDatabase) : IGetProjectPanels {

   override operator fun invoke(projectId: ProjectId): Flow<List<ProjectPanelsResult>> {
        return db.projectReadDao().getSimpleProjectPanels(projectId.id)
            .map { list->list.map { ProjectPanelsResult(
                PanelId(it.id),
                it.code,
                it.description,
            totalCurrent = Current(it.totalCurrent, Current.Unit.A)
            ) } }
    }
}