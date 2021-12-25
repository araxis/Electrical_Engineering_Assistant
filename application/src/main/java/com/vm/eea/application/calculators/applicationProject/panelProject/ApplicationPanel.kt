package com.vm.eea.application.calculators.applicationProject.panelProject

import com.vm.eea.application.CosPhi
import com.vm.eea.application.PanelId
import com.vm.eea.application.Voltage
import com.vm.eea.application.panel.CoincidenceFactor
import com.vm.eea.application.project.ProjectId

data class ApplicationPanel(val panelId:PanelId,
                            val projectId: ProjectId,
                            val lineToNullVoltage: Voltage,
                            val lineToLineVoltage: Voltage,
                            val coincidenceFactor: CoincidenceFactor,
                            val demandFactor:CosPhi)