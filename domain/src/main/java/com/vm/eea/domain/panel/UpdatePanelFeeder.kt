package com.vm.eea.domain.panel

import com.vm.eea.domain.ITransactionProvider
import com.vm.eea.domain.ISupplyPathService
import com.vm.eea.domain.panelToPanelRelation.IPanelToPanelRelationRepository
import com.vm.eea.domain.load.IMotorRepository

class UpdatePanelFeeder(
    private val transactionProvider: ITransactionProvider,
    private val panelRepository: IPanelRepository,
    private val motorRepository: IMotorRepository,
    private val relationRepository: IPanelToPanelRelationRepository,
    private val supplyPathService: ISupplyPathService
) {
    suspend operator fun invoke(panelId:Long,target:Panel){


            transactionProvider.runAsTransaction {

                val newPath= supplyPathService.getNextSupplyPath(target.projectId,target.powerSupplyPath)
               val panel= panelRepository.getPanel(panelId)
               panelRepository.updateSupplyPaths(target.projectId,panel.powerSupplyPath,newPath)
               motorRepository.replaceStartPaths(target.projectId,panel.powerSupplyPath,newPath)
               relationRepository.updateSourceFeeder(panel,target)
            }
    }
}