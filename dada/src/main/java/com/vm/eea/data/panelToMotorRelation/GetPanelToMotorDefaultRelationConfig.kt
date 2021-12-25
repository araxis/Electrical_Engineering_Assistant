package com.vm.eea.data.panelToMotorRelation

import com.vm.eea.application.*
import com.vm.eea.application.motor.addMotor.IGetPanelToMotorDefaultRelationConfig
import com.vm.eea.application.motor.addMotor.PanelToMotorRelationConfig
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetPanelToMotorDefaultRelationConfig(): IGetPanelToMotorDefaultRelationConfig {
    override suspend fun invoke(projectId: ProjectId): PanelToMotorRelationConfig {
        return  PanelToMotorRelationConfig(
                methodOfInstallation = MethodOfInstallation.A1,
                ambientTemperature = Temperature(25,UnitOfTemperature.C),
                groundTemperature = Temperature(25,UnitOfTemperature.C),
                conductor = Conductor.Copper,
                insulation = Insulation.ERP,
                maxVoltageDrop = VoltDrop(4),
                soilThermalResistivity = ThermalResistivity(2.5,UnitOfThermalResistivity.MW),
                circuitCount = CircuitCount(1),
                projectId = projectId
            )
    }
}