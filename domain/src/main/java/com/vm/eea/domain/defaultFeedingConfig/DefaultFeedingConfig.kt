package com.vm.eea.domain.defaultFeedingConfig

import com.vm.eea.domain.panel.IPanelRepository
import com.vm.eea.domain.project.IProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultFeedingConfig(private val projectRepository: IProjectRepository,
                           private val panelRepository:IPanelRepository) {

    suspend operator fun invoke(feederId:Long): Flow<FeedingConfig> {
        val feeder = panelRepository.getPanel(feederId)
        return projectRepository.getProjectFlow(feeder.projectId).map {
            FeedingConfig(
                methodOfInstallation = it.methodOfInstallation,
                maxVoltDrop = it.panelToPanelMaxVoltDrop,
                groundTemperature = it.groundTemperature,
                ambientTemperature = it.ambientTemperature,
                conductor = it.conductor,
                insulation = it.insulation,
                circuitCount = it.circuitInTheSameConduit,
                maxWireSize = it.maxWireSize,
                soilThermalResistivity = it.soilResistivity)
        }

    }

}