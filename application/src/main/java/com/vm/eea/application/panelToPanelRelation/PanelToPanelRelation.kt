package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.*


data class PanelToPanelRelation(val fromPanel: PanelId,
                                val toPanelId: PanelId,
                                val length: Length,
                                val maxVoltageDrop: VoltDrop,
                                val methodOfInstallation: MethodOfInstallation,
                                val ambientTemperature: Temperature,
                                val groundTemperature: Temperature,
                                val soilThermalResistivity: ThermalResistivity,
                                val conductor: Conductor,
                                val insulation: Insulation,
                                val circuitCount: CircuitCount

)