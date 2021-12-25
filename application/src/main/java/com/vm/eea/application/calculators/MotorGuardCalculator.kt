package com.vm.eea.application.calculators

import com.vm.eea.application.*
import com.vm.eea.application.bimetal.BimetalResult
import com.vm.eea.application.bimetal.IBimetalFinder
import com.vm.eea.application.protectionDevice.ProtectionDeviceType
import kotlin.math.sqrt

class MotorGuardCalculator(
    private val currentCalculator: CurrentCalculator,
    private val bimetalFinder: IBimetalFinder,
) {

   suspend operator fun invoke(current: Current,
                               startMode:StartMode,
                               protectionDeviceType: ProtectionDeviceType): BimetalResult {
        if(protectionDeviceType == ProtectionDeviceType.Tmb) return BimetalResult.UseLess
        return when(startMode){
            StartMode.Dol -> {
                val bimetal=bimetalFinder(current)
                BimetalResult.Use(bimetal.toResult())
            }
            StartMode.Sd -> {
                val appliedCurrent= current / sqrt(3.0)
                val bimetal=bimetalFinder(appliedCurrent)
                BimetalResult.Use(bimetal.toResult())

            }
            StartMode.SSd , StartMode.Vsd -> BimetalResult.UseLess
        }
    }

    suspend operator fun invoke(voltage: Voltage, power: Power,startMode: StartMode, system: PowerSystem, cosPhi: CosPhi, efficiency: Efficiency, keyType:ProtectionDeviceType): BimetalResult {
        val current=currentCalculator(voltage, power, system, cosPhi, efficiency)
        return invoke(current,startMode,keyType)
    }
}