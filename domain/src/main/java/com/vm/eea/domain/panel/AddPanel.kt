package com.vm.eea.domain.panel

import com.vm.eea.domain.ITransactionProvider
import com.vm.eea.domain.ISupplyPathService
import com.vm.eea.domain.Length
import com.vm.eea.domain.project.Project
import com.vm.eea.domain.panelToPanelRelation.IPanelToPanelRelationRepository
import com.vm.eea.domain.panelToPanelRelation.PanelToPanelRelation
import com.vm.eea.domain.project.IProjectRepository

class AddPanel(private val transactionProvider: ITransactionProvider,
               private val supplyPathService: ISupplyPathService,
               private val panelRepository: IPanelRepository,
               private val projectRepository: IProjectRepository,
               private val panelTopPanelRelationRepository: IPanelToPanelRelationRepository
) {



    suspend operator fun invoke(feeder: Panel, code:String, description: String,length: Length) {
        val supplyPath=supplyPathService.getNextSupplyPath(feeder.projectId,feeder.powerSupplyPath)
        val panel= Panel(feeder.projectId,code,description,false,supplyPath,0)
        transactionProvider.runAsTransaction {
            val project=projectRepository.getProject(feeder.projectId)
            val panelId= panelRepository.addPanel(panel)

            val relation=makeDefaultRelationFromProject(feeder,panel.copy(id=panelId),length,project)
            panelTopPanelRelationRepository.add(relation)
        }

    }


    private fun makeDefaultRelationFromProject(feeder:Panel, consumer:Panel, length: Length, project: Project):PanelToPanelRelation{
        return PanelToPanelRelation(feeder,consumer,length,
        project.panelToPanelMaxVoltDrop,project.methodOfInstallation,project.ambientTemperature,
        soilThermalResistivity = project.soilResistivity,
        groundTemperature = project.groundTemperature,
        conductor = project.conductor,circuitCount=project.circuitInTheSameConduit,
        insulation = project.insulation)
    }

}