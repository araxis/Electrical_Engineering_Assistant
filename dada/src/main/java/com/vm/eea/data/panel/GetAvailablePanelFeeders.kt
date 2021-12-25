package com.vm.eea.data.panel

import com.vm.eea.application.PanelId
import com.vm.eea.application.panel.IGetAvailablePanelFeeders
import com.vm.eea.application.panel.SimplePanel
import com.vm.eea.data.AppDatabase

class GetAvailablePanelFeeders(private val db: AppDatabase): IGetAvailablePanelFeeders {
    override suspend fun invoke(panelId: PanelId): List<SimplePanel> {

            val info=db.panelDao().getPanelPathInfo(panelId.id)
             return db.panelDao().getPanelsNotSupplyWith(info.projectId,info.supplyPath)
                 .map { SimplePanel(PanelId(it.id),it.code,it.description) }

    }
}