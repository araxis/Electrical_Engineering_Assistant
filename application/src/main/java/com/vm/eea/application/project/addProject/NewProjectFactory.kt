package com.vm.eea.application.project.addProject

class NewProjectFactory(private val getProjectDefaults: IGetProjectDefaults) {
    
    suspend operator fun invoke(code:String,description:String):NewProject{
        val projectDefaults=getProjectDefaults()
        return NewProject(
            code = code,
            description = description,
            altitude = projectDefaults.altitude,
            ambientTemperature = projectDefaults.ambientTemperature,
            unitOfWireSize = projectDefaults.unitOfWireSize,
            circuitInTheSameConduit = projectDefaults.circuitInTheSameConduit,
            conductor = projectDefaults.conductor,
            groundTemperature = projectDefaults.groundTemperature,
            insulation = projectDefaults.insulation,
            maxWireSize = projectDefaults.maxWireSize,
            methodOfInstallation = projectDefaults.methodOfInstallation,
            minWireSize = projectDefaults.minWireSize,
            singlePhaseVoltage = projectDefaults.singlePhaseVoltage,
            twoPhaseVoltage = projectDefaults.twoPhaseVoltage,
            panelToMotorMaxVoltDrop = projectDefaults.panelToMotorMaxVoltDrop,
            panelToPanelMaxVoltDrop = projectDefaults.panelToPanelMaxVoltDrop,
            singlePhaseCosPhi = projectDefaults.singlePhaseCosPhi,
            soilResistivity = projectDefaults.soilResistivity,
            standard = projectDefaults.standard,
            threePhaseCosPhi = projectDefaults.threePhaseCosPhi,
            twoPhaseCosPhi = projectDefaults.threePhaseCosPhi,
            threePhaseVoltage = projectDefaults.threePhaseVoltage,
            unitOfLength = projectDefaults.unitOfOfLength,
            unitOfPower = projectDefaults.unitOf,
            unitOfTemperature = projectDefaults.unitOfOfTemperature,
            unitOfOfVoltage = projectDefaults.unitOfOfVoltage,
        )
    }
}