package com.vm.eea.application.project

import com.vm.eea.application.Voltage
import com.vm.eea.application.project.IGetProjectDefaults
import com.vm.eea.application.project.Project
import com.vm.eea.application.project.ProjectId

class NewProjectFactory(private val getProjectDefaults: IGetProjectDefaults) {

    suspend operator fun invoke(code:String,description:String): Project {
        val projectDefaults=getProjectDefaults()
        return Project(
            ProjectId(0),
            code = code,
            description = description,
            altitude = projectDefaults.altitude,
            ambientTemperature = projectDefaults.ambientTemperature,
            circuitInTheSameConduit = projectDefaults.circuitInTheSameConduit,
            conductor = projectDefaults.conductor,
            groundTemperature = projectDefaults.groundTemperature,
            insulation = projectDefaults.insulation,
            maxWireSize = projectDefaults.maxWireSize,
            methodOfInstallation = projectDefaults.methodOfInstallation,
            minWireSize = projectDefaults.minWireSize,
            lineToNullVoltage = projectDefaults.lineNullVoltage,
            panelToMotorMaxVoltDrop = projectDefaults.panelToMotorMaxVoltDrop,
            panelToPanelMaxVoltDrop = projectDefaults.panelToPanelMaxVoltDrop,
            soilResistivity = projectDefaults.soilResistivity,
            standard = projectDefaults.standard,
            lineToLineVoltage = projectDefaults.lineLineVoltage,
        )
    }


    suspend operator fun invoke(code:String,description:String,
                                lineLineVoltage:Voltage,
                                lineNullVoltage:Voltage): Project {
        val projectDefaults=getProjectDefaults()
        return Project(ProjectId(0),
            code = code,
            description = description,
            altitude = projectDefaults.altitude,
            ambientTemperature = projectDefaults.ambientTemperature,
            circuitInTheSameConduit = projectDefaults.circuitInTheSameConduit,
            conductor = projectDefaults.conductor,
            groundTemperature = projectDefaults.groundTemperature,
            insulation = projectDefaults.insulation,
            maxWireSize = projectDefaults.maxWireSize,
            methodOfInstallation = projectDefaults.methodOfInstallation,
            minWireSize = projectDefaults.minWireSize,
            lineToNullVoltage = (lineNullVoltage to Voltage.Unit.V).value,
            panelToMotorMaxVoltDrop = projectDefaults.panelToMotorMaxVoltDrop,
            panelToPanelMaxVoltDrop = projectDefaults.panelToPanelMaxVoltDrop,
            soilResistivity = projectDefaults.soilResistivity,
            standard = projectDefaults.standard,
            lineToLineVoltage = (lineLineVoltage to Voltage.Unit.V).value,
        )
    }

}