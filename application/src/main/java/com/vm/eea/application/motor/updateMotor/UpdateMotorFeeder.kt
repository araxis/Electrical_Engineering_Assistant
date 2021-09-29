package com.vm.eea.application.motor.updateMotor

import com.vm.eea.application.ITransactionProvider
import com.vm.eea.application.PanelId
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.panelToMotorRelation.IPanelToMotorRelationRepository
import com.vm.eea.application.supplyPath.ISupplyPathService


class UpdateMotorFeeder(
    private val transactionProvider: ITransactionProvider,
    private val motorRepository: IMotorRepository,
    private val relationRepository: IPanelToMotorRelationRepository,
    private val supplyPathService: ISupplyPathService
)  {
     suspend operator fun invoke(loadId: MotorId, targetId: PanelId){

        transactionProvider.runAsTransaction {
            val newPath= supplyPathService.getNextSupplyPath(targetId)
            motorRepository.updatePath(loadId,newPath.supplyPath)
            relationRepository.updateSourceByConsumerId(loadId,targetId)
        }
    }
}