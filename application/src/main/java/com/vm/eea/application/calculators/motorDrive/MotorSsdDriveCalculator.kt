package com.vm.eea.application.calculators.motorDrive

import com.vm.eea.application.Current
import com.vm.eea.application.Power
import com.vm.eea.application.contactor.IContactorFinder
import com.vm.eea.application.ssd.ISsdFinder
import com.vm.eea.application.toResult

class MotorSsdDriveCalculator(
    private val ssdFinder: ISsdFinder,
    private val contactorFinder: IContactorFinder,
) {
    suspend operator fun invoke(
        power: Power,
        current:Current,
        isBiDirect: Boolean
    ): IMotorPowerDriveResult {


       val ssd=ssdFinder.withGreaterOrEqual(power)


        if (isBiDirect) {
            val contactor = contactorFinder.withGreaterOrEqual(current).toResult()
            return BiDirectMotorSsdDriveResult(ssd.toResult(),contactor,contactor)
        }

        return SsdDriveResult(ssd.toResult())

    }
}