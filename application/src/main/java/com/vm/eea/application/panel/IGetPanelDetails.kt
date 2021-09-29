package com.vm.eea.application.panel

import com.vm.eea.application.*
import kotlinx.coroutines.flow.Flow

interface IGetPanelDetails {
    suspend operator fun invoke(panelId: PanelId): Flow<PanelDetails>
}

data class PanelDetails(val id: PanelId,
                        val code: String,
                        val description: String,
                        val feederId: PanelId,
                        val feederCode:String,
                        val totalCurrent: Current,
                        val totalApparentPower: ApparentPower,
                        val totalReactivePower: ReactivePower,
                        val appliedCorrectionVar:ReactivePower,
                        val totalActivePower: Power,
                        val demandFactor: CosPhi,
                        val length: Length,
                        val maxVoltageDrop: VoltDrop,
                        val methodOfInstallation: MethodOfInstallation,
                        val ambientTemperature: Temperature,
                        val groundTemperature: Temperature,
                        val soilThermalResistivity: ThermalResistivity,
                        val conductor: Conductor,
                        val insulation: Insulation,
                        val circuitCount: CircuitCount,
                        val relationId: RelationId)