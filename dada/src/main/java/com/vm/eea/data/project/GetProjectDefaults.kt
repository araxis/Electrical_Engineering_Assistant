package com.vm.eea.data.project

import com.vm.eea.application.*
import com.vm.eea.application.project.IGetProjectDefaults
import com.vm.eea.application.project.ProjectDefaults

class GetProjectDefaults(): IGetProjectDefaults {
    override suspend fun invoke(): ProjectDefaults {
        return ProjectDefaults(
                powerSystem=PowerSystem.ThreePhase,
                lineNullVoltage= 220.0,
                lineLineVoltage= 415.0,
                altitude= 1200.m,
                methodOfInstallation= MethodOfInstallation.A1,
                ambientTemperature= 25.c,
                groundTemperature= 20.c,
                soilResistivity= ThermalResistivity(2.5,UnitOfThermalResistivity.MW),
                conductor= Conductor.Copper,
                insulation= Insulation.ERP,
                panelToPanelMaxVoltDrop= VoltDrop(4),
                panelToMotorMaxVoltDrop= VoltDrop(4),
                circuitInTheSameConduit= CircuitCount(1),
                maxWireSize= WireSize(2.5,UnitOfWireSize.MM2),
                minWireSize= WireSize(240,UnitOfWireSize.MM2),
                standard= Standard.NEC,
            )
        }

}