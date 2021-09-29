package com.vm.eea.data.panelToMotorRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.ThermalResistivity
import com.vm.eea.application.UnitOfThermalResistivity
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedThermalResistivity
import com.vm.eea.data.AppDatabase

class GetMotorFeedThermalResistivity(private val db:AppDatabase): IGetMotorFeedThermalResistivity {
    override suspend fun invoke(relationId: RelationId): List<SelectableItem<ThermalResistivity>> {
        val current =db.panelToMotorReadDao().getSoilResistivity(relationId.id)
        val defaults= listOf(.5,.7,1,1.5,2,2.5,3)
        return defaults.map { SelectableItem(ThermalResistivity(it,UnitOfThermalResistivity.MW),isSelected = it==current.value) }
    }
}