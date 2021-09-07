package com.vm.eea.domain.panel

import com.vm.eea.domain.ITransactionProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge

class GetAvailableFeeders(
    private val transactionProvider: ITransactionProvider,
    private val panelRepository: IPanelRepository,
) {
    suspend operator fun invoke(panelId:Long): Flow<List<Panel>> {
       return transactionProvider.runAsTransaction {
           panelRepository.getPanelFlow(panelId).flatMapMerge {
               panelRepository.getPanelsNotSupplyWith(it.projectId,it.powerSupplyPath)
           }

        }
    }
}