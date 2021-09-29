package com.vm.eea.data.panel

import com.vm.eea.application.PanelId
import com.vm.eea.application.panel.IGetPanelCode
import com.vm.eea.application.panel.SimplePanel
import com.vm.eea.data.AppDatabase

class GetPanelCode(private val db:AppDatabase): IGetPanelCode {
    override suspend fun invoke(panelId: PanelId): SimplePanel {
        return db.panelRadDao().getSimplePanel(panelId.id)
            .let { SimplePanel(PanelId(it.id),it.code,it.description) }
    }
}