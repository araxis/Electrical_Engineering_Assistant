package com.vm.eea.data.panel

import com.vm.eea.application.*
import com.vm.eea.application.panel.IGetPanelDetails
import com.vm.eea.application.panel.PanelDetails
import com.vm.eea.data.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPanelDetails(private val db:AppDatabase): IGetPanelDetails {
    override suspend fun invoke(panelId: PanelId): Flow<PanelDetails> {
        return db.panelRadDao().getFullPanelRelationViewFlow(panelId.id)
            .map {PanelDetails(PanelId(it.id),
            code = it.code,
            description = it.description,
            circuitCount = it.circuitCount,
            soilThermalResistivity = it.soilThermalResistivityUnit,
            maxVoltageDrop = it.maxVoltageDrop,
            length = it.length,
            insulation = it.insulation,
            conductor = it.conductor,
            groundTemperature = it.groundTemperature,
            ambientTemperature = it.ambientTemperature,
            methodOfInstallation = it.methodOfInstallation,
            relationId = RelationId(it.relationId),
            feederCode = it.feederCode?:"",
            feederId = PanelId(it.feederId?:-1),
            demandFactor = it.demandFactor,
            totalActivePower = Power(it.totalActivePower,Power.Unit.W),
            totalApparentPower = ApparentPower(it.totalApparentPower,ApparentPower.Unit.VA),
            totalReactivePower = ReactivePower(it.totalReactivePower,ReactivePower.Unit.VAr),
            totalCurrent = Current(it.totalCurrent,Current.Unit.A),
            appliedCorrectionVar = ReactivePower(it.appliedCorrectionVar,ReactivePower.Unit.VAr)
            )  }
    }
}