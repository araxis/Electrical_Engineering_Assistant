package com.vm.eea.data.panelToMotorRelation

import com.vm.eea.application.Environment
import com.vm.eea.application.RelationId
import com.vm.eea.application.Temperature
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedTemperature
import com.vm.eea.data.AppDatabase

class GetMotorFeedTemperature(private val db:AppDatabase): IGetMotorFeedTemperature {
    override suspend fun invoke(relationId: RelationId, environment: Environment): Temperature {
        return when(environment){
            Environment.Ambient -> db.panelToMotorReadDao().getAmbientTemperature(relationId.id)
            Environment.Ground -> db.panelToMotorReadDao().getGroundTemperature(relationId.id)
        }
    }
}