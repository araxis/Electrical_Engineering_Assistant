package com.vm.eea.ui.motor.motorDetails

import com.vm.eea.domain.Motor
import com.vm.eea.domain.panel.Panel
import com.vm.eea.domain.panelToMotorRelation.PanelToMotorRelation

class MotorDetails(val motor: Motor, val feeder: Panel, val relation: PanelToMotorRelation) {
}