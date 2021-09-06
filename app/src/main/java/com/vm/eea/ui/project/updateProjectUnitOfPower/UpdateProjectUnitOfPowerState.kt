package com.vm.eea.ui.project.updateProjectUnitOfPower

import com.vm.eea.domain.UnitOfPower
import com.vm.eea.ui.SelectableItem

data class UpdateProjectUnitOfPowerState(val defaults:List<SelectableItem<UnitOfPower>>) {
}
data class ProjectUnitOfPowerItem(val value: UnitOfPower, val isSelected:Boolean)
