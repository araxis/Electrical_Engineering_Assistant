package com.vm.eea.application.calculators.motorFullResult

import com.vm.eea.application.*
import com.vm.eea.application.calculators.*
import com.vm.eea.application.calculators.motorCapacitorDrive.MotorCapacitorDriveCalculator
import com.vm.eea.application.calculators.MotorCircuitBreakerCalculator
import com.vm.eea.application.calculators.motorDrive.MotorDolDriveCalculator
import com.vm.eea.application.calculators.motorDrive.MotorSdDriveCalculator
import com.vm.eea.application.calculators.motorDrive.MotorSsdDriveCalculator
import com.vm.eea.application.calculators.motorDrive.MotorVsdDriveCalculator
import com.vm.eea.application.protectionDevice.ProtectionType

class MotorResultCalculator(
    private val currentCalculator: CurrentCalculator,
    private val reactivePowerCalculator: ReactivePowerCalculator,
    private val apparentPowerCalculator: ApparentPowerCalculator,
    private val requiredVarCalculator: RequiredVarCalculator,
    private val circuitBreakerCalculator: MotorCircuitBreakerCalculator,
    private val guardCalculator: MotorGuardCalculator,
    private val dolDriveCalculator: MotorDolDriveCalculator,
    private val sdDriveCalculator: MotorSdDriveCalculator,
    private val ssdDriveCalculator: MotorSsdDriveCalculator,
    private val vsdDriveCalculator: MotorVsdDriveCalculator,
    private val torqueCalculator: TorqueCalculator,
    private val motorCapacitorDriveCalculator: MotorCapacitorDriveCalculator
) {

    suspend operator fun invoke(
        power: Power,
        voltage: Voltage,
        efficiency: Efficiency,
        cosPhi: CosPhi,
        ratedSpeed:Speed,
        demandFactor:CosPhi,
        startMode: StartMode,
        protectionType: ProtectionType,
        hasOverLoadProtection: Boolean,
        isBiDirect:Boolean,
        system: PowerSystem
    ):MotorCalculationResult{
        val current=currentCalculator(voltage,power,system, cosPhi, efficiency)
        val reactivePower=reactivePowerCalculator(power,cosPhi)
        val apparentPower=apparentPowerCalculator(power, reactivePower)
        val requiredReactivePower=requiredVarCalculator(power,cosPhi,demandFactor)
        val torque=torqueCalculator(power,ratedSpeed)
        val protection=circuitBreakerCalculator(current, protectionType, hasOverLoadProtection)
        val guard=guardCalculator(current,startMode,protection.keyType)
        val powerDrive=when(startMode){
            StartMode.Dol -> dolDriveCalculator(current,isBiDirect)
            StartMode.Sd -> sdDriveCalculator(current,isBiDirect)
            StartMode.SSd -> ssdDriveCalculator(power,current,isBiDirect)
            StartMode.Vsd -> vsdDriveCalculator(power)
        }
        val capacitorDrive=motorCapacitorDriveCalculator(requiredReactivePower)

        return MotorCalculationResult(current,reactivePower,apparentPower,requiredReactivePower,torque,protection,
        powerDrive,guard,capacitorDrive)

    }

}