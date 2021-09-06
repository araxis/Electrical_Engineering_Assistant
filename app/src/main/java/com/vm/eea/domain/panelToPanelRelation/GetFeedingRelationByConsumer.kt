package com.vm.eea.domain.panelToPanelRelation

class GetFeedingRelationByConsumer(private val panelToPanelRelationRepository: IPanelToPanelRelationRepository) {

    suspend operator fun invoke(panelId:Long)=panelToPanelRelationRepository.getFeederRelationByConsumerId(panelId)
  //  suspend fun invoke(relationId:Long)=panelToPanelRelationRepository.getFeederRelationById(relationId)
}