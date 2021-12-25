package com.vm.eea.data.panelToPanelRelation

import com.vm.eea.application.panel.addPanel.IGetDefaultPanelToPanelRelationConfigs
import com.vm.eea.application.panel.addPanel.PanelToPanelRelationConfig
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetPanelToPanelDefaultRelationConfig(private val db:AppDatabase):
    IGetDefaultPanelToPanelRelationConfigs {
    override suspend fun invoke(projectId: ProjectId): PanelToPanelRelationConfig {
        return db.projectDao().get(projectId.id)
            .let { PanelToPanelRelationConfig(
                methodOfInstallation = it.methodOfInstallation,
                ambientTemperature = it.ambientTemperature,
                groundTemperature = it.groundTemperature,
                conductor = it.conductor,
                insulation = it.insulation,
                maxVoltageDrop = it.panelToMotorMaxVoltDrop,
                soilThermalResistivity = it.soilResistivity,
                circuitCount = it.circuitInTheSameConduit,
                projectId = ProjectId(it.id)
            ) }
    }
}