package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.Length
import com.vm.eea.application.RelationId

interface IGetRelationLength {

    suspend  operator fun invoke(relationId:RelationId):Length
}