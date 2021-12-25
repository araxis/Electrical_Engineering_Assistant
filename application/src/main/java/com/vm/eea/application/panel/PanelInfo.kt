package com.vm.eea.application.panel

import com.vm.eea.application.*

data class PanelInfo(val current: Current,
                     val coincidenceFactor: CoincidenceFactor,
                     val demandFactor:CosPhi,
                     val activePower: Power,
                     val reactivePower: ReactivePower,
                     val apparentPower: ApparentPower,
                     val lineNullVoltage:Voltage,
                     val lineLineVoltage: Voltage   )