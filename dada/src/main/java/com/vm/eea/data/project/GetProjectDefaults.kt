package com.vm.eea.data.project

import com.vm.eea.application.project.addProject.IGetProjectDefaults
import com.vm.eea.application.project.addProject.ProjectDefaults
import com.vm.eea.data.AppDatabase

class GetProjectDefaults(private val db:AppDatabase):IGetProjectDefaults {
    override suspend fun invoke(): ProjectDefaults {
        return db.defaultsDao().getProjectDefaults().let { 
            ProjectDefaults(
                powerSystem=it.powerSystem,
                unitOfOfVoltage=it.unitOfVoltage,
                unitOf= it.unitOfPower,
                unitOfOfLength= it.unitOfLength,
                unitOfOfTemperature= it.unitOfTemperature,
                unitOfWireSize= it.unitOfWireSize,
                singlePhaseVoltage= it.singlePhaseVoltage,
                twoPhaseVoltage=it.twoPhaseVoltage,
                threePhaseVoltage= it.threePhaseVoltage,
                altitude= it.altitude,
                methodOfInstallation= it.methodOfInstallation,
                ambientTemperature= it.ambientTemperature,
                groundTemperature= it.groundTemperature,
                soilResistivity= it.soilResistivity,
                conductor= it.conductor,
                insulation= it.insulation,
                singlePhaseCosPhi= it.singlePhaseCosPhi,
                threePhaseCosPhi= it.threePhaseCosPhi,
                panelToPanelMaxVoltDrop= it.panelToPanelMaxVoltDrop,
                panelToMotorMaxVoltDrop= it.panelToMotorMaxVoltDrop,
                circuitInTheSameConduit=it.circuitInTheSameConduit,
                maxWireSize=it.maxWireSize,
                minWireSize=it.minWireSize,
                standard= it.standard,
                twoPhaseCosPhi = it.twoPhaseCosPhi
            )
        }
    }
}