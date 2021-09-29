package com.vm.eea.application.motor.addMotor

import com.vm.eea.application.*
import com.vm.eea.application.motor.*
import com.vm.eea.application.panelToMotorRelation.IPanelToMotorRelationRepository
import com.vm.eea.application.panelToMotorRelation.PanelToMotorRelation
import com.vm.eea.application.supplyPath.ISupplyPathService


class AddMotor(private val transactionProvider: ITransactionProvider,
               private val supplyPathService: ISupplyPathService,
               private val codeDuplicateChecker:IMotorCodeExistChecker,
               private val motorRepository:IMotorRepository,
               private val relationRepository:IPanelToMotorRelationRepository,
               private val getMotorRelationConfig:IGetPanelToMotorDefaultRelationConfig
) {

    suspend operator fun invoke(code:String,
                                description:String,
                                power: Power,
                                powerfactor: CosPhi,
                                demandFactor: CosPhi,
                                applyLocalCosPhiCorrection:Boolean,
                                efficiency: Efficiency,
                                system: PowerSystem,
                                startMode: StartMode,
                                feedingMode: FeedingMode,
                                feederId: PanelId,
                                feedLineLength: Length

    ){
        transactionProvider.runAsTransaction {
            val motorCode=MotorCode(code)
            val isExist=codeDuplicateChecker(motorCode)
            require(!isExist){"Duplicated code"}
            transactionProvider.runAsTransaction {
                val result=supplyPathService.getNextSupplyPath(feederId)
                val defaults=getMotorRelationConfig(feederId)
                val powerInWatt=power to Power.Unit.W
                val motor=Motor(defaults.projectId,motorCode,description,power,powerfactor,
                    demandFactor,
                    applyLocalCosPhiCorrection,
                    efficiency,system,startMode, ServiceMode.Service,result.supplyPath,feedingMode,powerInWatt.value)
                val motorId=motorRepository.add(motor)
                val relation=createDefaultRelation(feederId,motorId,defaults,feedLineLength)
                relationRepository.add(relation)
            }

        }


    }

    private fun createDefaultRelation(feederId:PanelId,motorId:MotorId,defaults:PanelToMotorRelationConfig,length:Length):PanelToMotorRelation{
        return PanelToMotorRelation(
            feederId,
            motorId,
            length,
            defaults.maxVoltageDrop,
            defaults.methodOfInstallation,
            defaults.ambientTemperature,
            soilThermalResistivity = defaults.soilThermalResistivity,
            groundTemperature = defaults.groundTemperature,
            conductor = defaults.conductor,
            circuitCount = defaults.circuitCount,
            insulation = defaults.insulation
        )
    }
}