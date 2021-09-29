package com.vm.eea.application.project

import com.vm.eea.application.Current
import com.vm.eea.application.PanelId
import kotlinx.coroutines.flow.Flow

typealias ProjectPanelsResult =IGetProjectPanels.Result
interface IGetProjectPanels {
    operator fun invoke(projectId: ProjectId): Flow<List<ProjectPanelsResult>>

    data class Result(val id: PanelId, val code:String, val description:String, val totalCurrent: Current)
}