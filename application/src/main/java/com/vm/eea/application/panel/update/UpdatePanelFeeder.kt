package com.vm.eea.application.panel.update

import com.vm.eea.application.ITransactionProvider
import com.vm.eea.application.PanelId
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.panelToPanelRelation.IPanelToPanelRelationRepository
import com.vm.eea.application.supplyPath.ISupplyPathService



class UpdatePanelFeeder(private val transactionProvider: ITransactionProvider,
                        private val panelRepository: IPanelRepository,
                        private val motorRepository: IMotorRepository,
                        private val relationRepository: IPanelToPanelRelationRepository,
                        private val supplyPathService: ISupplyPathService
) {

    suspend operator fun invoke(panelId: PanelId, feederId: PanelId){
        transactionProvider.runAsTransaction {
            val newSupplyPath=supplyPathService.getNextSupplyPath(feederId)
            panelRepository.replaceSupplyPaths(newSupplyPath.projectId,newSupplyPath.supplyPath.parent,newSupplyPath.supplyPath)
            motorRepository.replaceSupplyPaths(newSupplyPath.projectId,newSupplyPath.supplyPath.parent,newSupplyPath.supplyPath)
            relationRepository.updateSourceByConsumerId(panelId,feederId)
        }

    }
}