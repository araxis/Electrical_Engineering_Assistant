package com.vm.eea.application.calculators.panelFullResult

import com.vm.eea.application.*
import com.vm.eea.application.bimetal.BimetalResult
import com.vm.eea.application.calculators.motorCapacitorDrive.MotorCapacitorDriveResult
import com.vm.eea.application.calculators.motorDrive.IMotorPowerDriveResult
import com.vm.eea.application.panel.CoincidenceFactor
import com.vm.eea.application.panel.PanelInfo
import com.vm.eea.application.protectionDevice.ProtectionDeviceResult

data class PanelCalculationResult(
    val demandFactor:CosPhi,
    val coincidenceFactor: CoincidenceFactor,
    val lineLineVoltage: Voltage,
    val lineNullVoltage: Voltage,
    val current: Current,
    val activePower: Power,
    val reactivePower: ReactivePower,
    val apparentPower: ApparentPower,
    val requiredReactivePower: ReactivePower,
    val cosPhi:CosPhi,
    val protection: ProtectionDeviceResult
)