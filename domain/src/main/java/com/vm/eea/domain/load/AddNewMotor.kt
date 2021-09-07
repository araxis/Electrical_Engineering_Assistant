package com.vm.eea.domain.load

import com.vm.eea.domain.ITransactionProvider
import com.vm.eea.domain.*
import com.vm.eea.domain.panel.IPanelRepository
import com.vm.eea.domain.PanelId
import com.vm.eea.domain.panelToMotorRelation.IPanelToMotorRelationRepository
import com.vm.eea.domain.panelToMotorRelation.PanelToMotorRelation
import com.vm.eea.domain.project.IProjectRepository
import com.vm.eea.domain.project.Project


class AddNewMotor(private val transactionProvider: ITransactionProvider,
                  private val motorRepository: IMotorRepository,
                  private val panelRepository:IPanelRepository,
                  private val projectRepository:IProjectRepository,
                  private val relationRepository: IPanelToMotorRelationRepository,
                  private val supplyPathService: ISupplyPathService
) {

    suspend operator fun invoke(code:String,
                                description:String,
                                power: Power,
                                powerfactor: PowerFactor,
                                demandFactor: PowerFactor,
                                efficiency: Efficiency,
                                system: PowerSystem,
                                feedingMode: FeedingMode,
                                feedLineLength: Length,
                                feederId: PanelId
    ){

        transactionProvider.runAsTransaction {
            val feeder=panelRepository.getPanel(feederId.id)
            val supplyPath=supplyPathService.getNextSupplyPath(feeder.projectId,feeder.powerSupplyPath)
            val project=projectRepository.getProject(feeder.projectId)
            val newMotor= Motor(code,description,power,powerfactor,
                demandFactor,efficiency,system,
                ServiceMode.Service,
                feeder.projectId,supplyPath,feedingMode, LoadId(0))
            val motorId=motorRepository.add(newMotor)
            val relation=makeDefaultRelationFromProject(feederId,motorId,feedLineLength,project)
            relationRepository.add(relation)
        }

    }

    private fun makeDefaultRelationFromProject(feederId: PanelId, consumerId: LoadId, length: Length, project: Project): PanelToMotorRelation {
        return PanelToMotorRelation(feederId,consumerId,length,
            project.panelToPanelMaxVoltDrop,project.methodOfInstallation,project.ambientTemperature,
            soilThermalResistivity = project.soilResistivity,
            groundTemperature = project.groundTemperature,
            conductor = project.conductor,circuitCount=project.circuitInTheSameConduit,
            insulation = project.insulation)
    }
}