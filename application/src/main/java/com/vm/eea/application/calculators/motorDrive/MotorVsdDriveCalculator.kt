package com.vm.eea.application.calculators.motorDrive

import com.vm.eea.application.*
import com.vm.eea.application.vsd.IVsdFinder

class MotorVsdDriveCalculator(
    private val vsdFinder: IVsdFinder,
) {
    suspend operator fun invoke(
        power: Power,
    ): MotorVsdResult {


       val vsd=vsdFinder.withGreaterOrEqual(power)
       return MotorVsdResult(vsd.toResult())



    }
}