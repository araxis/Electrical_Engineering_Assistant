package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.*
import com.vm.eea.application.motor.MotorId


data class PanelToMotorRelation(val fromPanel: PanelId,
                                val toMotorId: MotorId,
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