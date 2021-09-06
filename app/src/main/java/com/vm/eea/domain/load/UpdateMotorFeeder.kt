package com.vm.eea.domain.load

import com.vm.eea.ITransactionProvider
import com.vm.eea.domain.ISupplyPathService
import com.vm.eea.domain.panel.IPanelRepository
import com.vm.eea.domain.panel.PanelId
import com.vm.eea.domain.panelToMotorRelation.IPanelToMotorRelationRepository

class UpdateMotorFeeder(
    private val transactionProvider: ITransactionProvider,
    private val panelRepository: IPanelRepository,
    private val motorRepository: IMotorRepository,
    private val relationRepository: IPanelToMotorRelationRepository,
    private val supplyPathService: ISupplyPathService
) {
    suspend operator fun invoke(loadId:LoadId,targetId: PanelId){


        transactionProvider.runAsTransaction {
            val target=panelRepository.getPanel(targetId.id)
            val newPath= supplyPathService.getNextSupplyPath(target.projectId,target.powerSupplyPath)
            motorRepository.updatePath(loadId,newPath)
            relationRepository.updateSourceFeeder(loadId,targetId)
        }
    }
}