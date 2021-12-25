package com.vm.eea.application.motor.addMotor

import com.vm.eea.application.*
import com.vm.eea.application.motor.*
import com.vm.eea.application.panelToMotorRelation.IPanelToMotorRelationRepository
import com.vm.eea.application.panelToMotorRelation.PanelToMotorRelation
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.application.supplyPath.ISupplyPathService


class AddMotor(private val transactionProvider: ITransactionProvider,
               private val supplyPathService: ISupplyPathService,
               private val motorRepository:IMotorRepository,
               private val relationRepository:IPanelToMotorRelationRepository,
               private val getMotorRelationConfig:IGetPanelToMotorDefaultRelationConfig,
               private val codeGenerator: IMotorUniqueCodeGenerator
) {

    suspend operator fun invoke(code:String,
                                description:String,
                                power: Power,
                                powerfactor: CosPhi,
                                demandFactor: CosPhi,
                                applyLocalCosPhiCorrection:Boolean,
                                efficiency: Efficiency,
                                ratedSpeed:Speed,
                                isBiDirect:Boolean,
                                hasOverloadRelay:Boolean,
                                protectionType: ProtectionType,
                                system: PowerSystem,
                                startMode: StartMode,
                                feedingMode: FeedingMode,
                                feederId: PanelId,
                                feedLineLength: Length

    ){
        transactionProvider.runAsTransaction {
            val result=supplyPathService.getNextSupplyPath(feederId)
            val motorCode=MotorCode(code)
            transactionProvider.runAsTransaction {
                val defaults=getMotorRelationConfig(result.projectId)
                val powerInWatt=power to Power.Unit.W
                val motor=Motor(defaults.projectId,MotorId(-1),
                    motorCode,description,power,powerfactor,
                    demandFactor,
                    applyLocalCosPhiCorrection,
                    efficiency,system,startMode,ratedSpeed,
                    ServiceMode.Service,
                    result.supplyPath,
                    feedingMode,powerInWatt.value,isBiDirect,hasOverloadRelay,protectionType)
                   addMotor(motor, feederId, feedLineLength)
            }

        }


    }

   private suspend fun addMotor(motor: Motor,feederId: PanelId,feedLineLength: Length){
        transactionProvider.runAsTransaction {
            val defaults=getMotorRelationConfig(motor.projectId)
            val motorId=motorRepository.add(motor)
            val relation=createDefaultRelation(feederId,motorId,defaults,feedLineLength)
            relationRepository.add(relation)
        }


    }

    suspend fun addCopy(motorId: MotorId,panelId: PanelId){
        transactionProvider.runAsTransaction {
            val result=supplyPathService.getNextSupplyPath(panelId)
            val motor=motorRepository.getMotor(motorId)
            val newCode=codeGenerator()
            val copyMotor=motor.copy(code = MotorCode(newCode),powerSupplyPath = result.supplyPath)
            val relation=relationRepository.getByConsumer(motorId)
            val newMotorId=motorRepository.add(copyMotor)
            val relationCopy=relation.copy(toMotorId = newMotorId)
            relationRepository.add(relationCopy)
        }

    }

    suspend operator fun invoke(
                                power: Power,
                                powerfactor: CosPhi,
                                demandFactor: CosPhi,
                                applyLocalCosPhiCorrection:Boolean,
                                efficiency: Efficiency,
                                ratedSpeed:Speed,
                                isBiDirect:Boolean,
                                hasOverloadRelay:Boolean,
                                protectionType: ProtectionType,
                                system: PowerSystem,
                                startMode: StartMode,
                                feedingMode: FeedingMode,
                                feederId: PanelId,
                                feedLineLength: Length

    ){
        val code=codeGenerator()
        val description=""
        invoke(code,description,power, powerfactor, demandFactor, applyLocalCosPhiCorrection, efficiency, ratedSpeed, isBiDirect, hasOverloadRelay,
            protectionType, system, startMode, feedingMode, feederId, feedLineLength)


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