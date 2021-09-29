package com.vm.eea.data.panel

import com.vm.eea.application.PanelId
import com.vm.eea.application.panel.IGetSimplePanels
import com.vm.eea.application.panel.SimplePanel
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetSimplePanels(private val db:AppDatabase): IGetSimplePanels {
    override suspend fun invoke(projectId: ProjectId): List<SimplePanel> {
       return db.panelRadDao().getSimplePanels(projectId.id)
           .map { SimplePanel(PanelId(it.id),it.code,it.description) }
    }
}