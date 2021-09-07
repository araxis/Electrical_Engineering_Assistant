package com.vm.eea.domain.panelToPanelRelation

import com.vm.eea.domain.*
import com.vm.eea.domain.panel.Panel

data class PanelToPanelRelation(val fromPanel: Panel,
                                val toPanelId:Panel,
                                val length: Length,
                                val maxVoltageDrop: VoltDrop,
                                val methodOfInstallation: MethodOfInstallation,
                                val ambientTemperature: Temperature,
                                val groundTemperature: Temperature,
                                val soilThermalResistivity: ThermalResistivity,
                                val conductor: Conductor,
                                val insulation: Insulation,
                                val circuitCount: CircuitCount,
                                val id: RelationId = RelationId(0)
)
