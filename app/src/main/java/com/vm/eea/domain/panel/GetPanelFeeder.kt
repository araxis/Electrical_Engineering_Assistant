package com.vm.eea.domain.panel

import com.vm.eea.ITransactionProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge

class GetPanelFeeder(private val transactionProvider: ITransactionProvider, private val panelRepository: IPanelRepository) {

    suspend operator fun invoke(panelId:Long): Flow<Panel> {

        return transactionProvider.runAsTransaction {
            panelRepository.getPanelFlow(panelId).flatMapMerge {
                panelRepository.getPanel(it.projectId, it.powerSupplyPath.parent)
            }
        }
    }

}