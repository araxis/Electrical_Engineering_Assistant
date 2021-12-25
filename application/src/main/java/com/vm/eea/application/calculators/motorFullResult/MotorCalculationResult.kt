package com.vm.eea.application.calculators.motorFullResult

import com.vm.eea.application.ApparentPower
import com.vm.eea.application.Current
import com.vm.eea.application.ReactivePower
import com.vm.eea.application.Torque
import com.vm.eea.application.bimetal.BimetalResult
import com.vm.eea.application.calculators.motorCapacitorDrive.MotorCapacitorDriveResult
import com.vm.eea.application.calculators.motorDrive.IMotorPowerDriveResult
import com.vm.eea.application.protectionDevice.ProtectionDeviceResult

data class MotorCalculationResult(
    val current: Current,
    val reactivePower: ReactivePower,
    val apparentPower: ApparentPower,
    val requiredReactivePower: ReactivePower,
    val torque: Torque,
    val protection: ProtectionDeviceResult,
    val powerDrive: IMotorPowerDriveResult,
    val overLoadProtection: BimetalResult,
    val capacitor: MotorCapacitorDriveResult
)