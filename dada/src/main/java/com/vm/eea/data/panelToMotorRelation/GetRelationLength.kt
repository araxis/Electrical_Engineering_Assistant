package com.vm.eea.data.panelToMotorRelation

import com.vm.eea.application.Length
import com.vm.eea.application.RelationId
import com.vm.eea.application.panelToMotorRelation.IGetRelationLength
import com.vm.eea.data.AppDatabase

class GetRelationLength(private val db:AppDatabase):IGetRelationLength {
    override suspend fun invoke(relationId: RelationId): Length {
        return db.panelToMotorReadDao().getLength(relationId.id)
    }
}