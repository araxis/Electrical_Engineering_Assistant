package com.vm.eea.application.panel.addPanel


import com.vm.eea.application.CosPhi
import com.vm.eea.application.ITransactionProvider
import com.vm.eea.application.Length
import com.vm.eea.application.PanelId
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.panel.Panel
import com.vm.eea.application.panel.PanelCode
import com.vm.eea.application.panelToPanelRelation.IPanelToPanelRelationRepository
import com.vm.eea.application.panelToPanelRelation.PanelToPanelRelation
import com.vm.eea.application.supplyPath.ISupplyPathService


class AddPanel(
    private val transactionProvider: ITransactionProvider,
    private val supplyPathService: ISupplyPathService,
    private val getDefaultPanelToPanelRelationConfigs: IGetDefaultPanelToPanelRelationConfigs,
    private val panelRepository: IPanelRepository,
    private val relationRepository:IPanelToPanelRelationRepository
) {

    suspend operator fun invoke(code:String, description: String, demandFactor: CosPhi, feederId: PanelId, feedLength: Length){
        transactionProvider.runAsTransaction {
            val panelCode=PanelCode(code)
            val supplyPath=supplyPathService.getNextSupplyPath(feederId)
            val defaults=getDefaultPanelToPanelRelationConfigs(feederId)
            val panel=Panel(defaults.projectId,panelCode,description,false,demandFactor,supplyPath.supplyPath)
            val newPanelId=panelRepository.add(panel)
            val relation=createDefaultRelation(feederId,newPanelId,defaults,feedLength)
            relationRepository.add(relation)
        }

    }

    private  fun createDefaultRelation(feederId:PanelId,consumerId:PanelId,defaults:PanelToPanelRelationConfig,length: Length): PanelToPanelRelation {

        return PanelToPanelRelation(feederId,consumerId,length,
            defaults.maxVoltageDrop,defaults.methodOfInstallation,defaults.ambientTemperature,
            defaults.groundTemperature,defaults.soilThermalResistivity,defaults.conductor,defaults.insulation,defaults.circuitCount
        )
    }
}