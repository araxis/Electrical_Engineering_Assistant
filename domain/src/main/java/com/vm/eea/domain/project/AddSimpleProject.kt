package com.vm.eea.domain.project

import com.vm.eea.domain.ITransactionProvider
import com.vm.eea.domain.defaultAltitude.AppDefaults
import com.vm.eea.domain.appDefaults.IAppDefaultsRepository
import com.vm.eea.domain.panel.AddMdp

class AddSimpleProject(private val transactionProvider: ITransactionProvider,
                       private val addMdp:AddMdp,
                       private val projectRepository: IProjectRepository,
                       private val appDefaultsRepository: IAppDefaultsRepository
) {

    suspend operator fun invoke(simpleProject: SimpleNewProject){
        transactionProvider.runAsTransaction {
            val appDefaults=appDefaultsRepository.getDefaults()
            val entity = defaultProject(simpleProject, appDefaults)
           val projectId= projectRepository.addProject(entity)
            addMdp(projectId,"MDP","Main distribution panel")
        }

    }

    private fun defaultProject(
        simpleProject: SimpleNewProject,
        appDefaults: AppDefaults,
    ): Project {
        return Project(
            code = simpleProject.code,
            description = simpleProject.description,
            altitude = appDefaults.altitude,
            ambientTemperature = appDefaults.ambientTemperature,
            unitOfWireSize = appDefaults.unitOfWireSize,
            circuitInTheSameConduit = appDefaults.circuitInTheSameConduit,
            conductor = appDefaults.conductor,
            groundTemperature = appDefaults.groundTemperature,
            insulation = appDefaults.insulation,
            maxWireSize = appDefaults.maxWireSize,
            methodOfInstallation = appDefaults.methodOfInstallation,
            minWireSize = appDefaults.minWireSize,
            isDeleted = false,
            singlePhaseVoltage = appDefaults.singlePhaseVoltage,
            twoPhaseVoltage=appDefaults.twoPhaseVoltage,
            panelToMotorMaxVoltDrop = appDefaults.panelToMotorMaxVoltDrop,
            panelToPanelMaxVoltDrop = appDefaults.panelToPanelMaxVoltDrop,
            singlePhasePowerFactor = appDefaults.singlePhasePowerFactor,
            soilResistivity = appDefaults.soilResistivity,
            standard = appDefaults.standard,
            threePhasePowerFactor =appDefaults.threePhasePowerFactor,
            twoPhasePowerFactor=appDefaults.threePhasePowerFactor,
            threePhaseVoltage = appDefaults.threePhaseVoltage,
            unitOfLength = appDefaults.unitOfOfLength,
            unitOfPower = appDefaults.unitOfOfPower,
            unitOfTemperature = appDefaults.unitOfOfTemperature,
            unitOfOfVoltage = appDefaults.unitOfOfVoltage,
            id = ProjectId(0)
        )
    }
}

data class SimpleNewProject(val code:String,val description: String)