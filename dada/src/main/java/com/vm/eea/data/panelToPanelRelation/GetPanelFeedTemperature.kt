package com.vm.eea.data.panelToPanelRelation

import com.vm.eea.application.Environment
import com.vm.eea.application.RelationId
import com.vm.eea.application.Temperature
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedTemperature
import com.vm.eea.data.AppDatabase

class GetPanelFeedTemperature(private val db:AppDatabase): IGetMotorFeedTemperature {
    override suspend fun invoke(relationId: RelationId, environment: Environment): Temperature {
        return when(environment){
            Environment.Ambient -> db.panelToPanelReadDao().getAmbientTemperature(relationId.id)
            Environment.Ground -> db.panelToPanelReadDao().getGroundTemperature(relationId.id)
        }
    }
}