package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem

interface IGetMotorRelationMethodOfInstallation {
   suspend  operator fun invoke(relationId: RelationId): List<SelectableItem<MethodOfInstallation>>
}