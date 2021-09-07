package com.vm.eea.ui.motor.motorDetails

import com.vm.eea.domain.ITransactionProvider
import com.vm.eea.domain.load.IMotorRepository
import com.vm.eea.domain.LoadId
import com.vm.eea.domain.panel.IPanelRepository
import com.vm.eea.domain.panelToMotorRelation.IPanelToMotorRelationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge

class GetMotorDetails(
    private val transactionProvider: ITransactionProvider,
    private val motorRepository: IMotorRepository,
    private val panelRepository: IPanelRepository,
    private val relationRepository: IPanelToMotorRelationRepository,
) {

      operator fun invoke(motorId: LoadId): Flow<MotorDetails>{
         return relationRepository.getFeederRelationByConsumerId(motorId).flatMapMerge {relation->
              motorRepository.getMotorFlow(relation.toLoadId)
                   .combine(panelRepository.getPanelFlow(relation.fromPanelId.id)){motor,panel->
                   MotorDetails(motor,panel,relation)
              }
         }
    }

}