package com.vm.eea.data.panelToMotorRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.VoltDrop
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedMaxVoltDrop
import com.vm.eea.data.AppDatabase

class GetMotorRelationMaxVoltDrop(private val db:AppDatabase):IGetMotorFeedMaxVoltDrop {
    override suspend fun invoke(relationId: RelationId): VoltDrop {
        return db.panelToMotorReadDao().getMaxVoltDrop(relationId.id)
    }


}