package com.vm.eea.application.calculators.applicationProject.panelProject

import com.vm.eea.application.ITransactionProvider
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.project.IProjectRepository

class UpdateApplicationPanelInfo(
    private val projectRepository: IProjectRepository,
    private val panelRepository: IPanelRepository,
    private val transactionProvider: ITransactionProvider)   {

    suspend operator fun invoke(item:ApplicationPanel){
        transactionProvider.runAsTransaction {
            projectRepository.updateLineToLineVoltage(item.projectId,item.lineToLineVoltage.value)
            projectRepository.updateLineToNullVoltage(item.projectId,item.lineToNullVoltage.value)
            panelRepository.updateDemandFactor(item.panelId,item.demandFactor)
            panelRepository.updateCoincidenceFactor(item.panelId,item.coincidenceFactor)

        }
    }
}