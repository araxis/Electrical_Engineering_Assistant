package com.vm.eea.application.motor.addMotor

import com.vm.eea.application.*
import com.vm.eea.application.project.ProjectId


data class PanelToMotorRelationConfig(val maxVoltageDrop: VoltDrop,
                                      val methodOfInstallation: MethodOfInstallation,
                                      val ambientTemperature: Temperature,
                                      val groundTemperature: Temperature,
                                      val soilThermalResistivity: ThermalResistivity,
                                      val conductor: Conductor,
                                      val insulation: Insulation,
                                      val circuitCount: CircuitCount,
                                      val projectId: ProjectId
)