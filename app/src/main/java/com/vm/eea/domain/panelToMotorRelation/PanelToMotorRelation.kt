package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.*
import com.vm.eea.domain.load.LoadId
import com.vm.eea.domain.panel.PanelId

data class PanelToMotorRelation(val fromPanelId: PanelId,
                                val toLoadId:LoadId,
                                val length: Length,
                                val maxVoltageDrop: VoltDrop,
                                val methodOfInstallation: MethodOfInstallation,
                                val ambientTemperature: Temperature,
                                val groundTemperature: Temperature,
                                val soilThermalResistivity: ThermalResistivity,
                                val conductor: Conductor,
                                val insulation: Insulation,
                                val circuitCount: CircuitCount,
                                val id:Long=0)
