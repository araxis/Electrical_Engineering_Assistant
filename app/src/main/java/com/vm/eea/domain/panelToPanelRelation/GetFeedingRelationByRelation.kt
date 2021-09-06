package com.vm.eea.domain.panelToPanelRelation

class GetFeedingRelationByRelation(private val panelToPanelRelationRepository: IPanelToPanelRelationRepository) {


    suspend operator fun invoke(relationId:Long)=panelToPanelRelationRepository.getFeederRelationById(relationId)
}