package com.vm.eea.data.panelToPanelRelation

import com.vm.eea.application.Length
import com.vm.eea.application.RelationId
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedLength
import com.vm.eea.data.AppDatabase

class GetPanelFeedLength(private val db:AppDatabase):IGetPanelFeedLength {
    override suspend fun invoke(relationId: RelationId): Length {
        return db.panelToPanelReadDao().getLength(relationId.id)
    }
}