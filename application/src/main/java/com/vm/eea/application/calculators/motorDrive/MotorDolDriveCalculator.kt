package com.vm.eea.application.calculators.motorDrive

import com.vm.eea.application.Current
import com.vm.eea.application.contactor.IContactorFinder
import com.vm.eea.application.toResult

class MotorDolDriveCalculator(
    private val contactorFinder: IContactorFinder
) {
    suspend operator fun invoke(
        current: Current,
        isBiDirect: Boolean
    ): IMotorPowerDriveResult {

        val mainContactor = contactorFinder.withGreaterOrEqual(current).toResult()

        if (isBiDirect) return MotorBiDirectDolDriveResult(mainContactor = mainContactor,mainContactor)

        return  MotorDolDriveResult(mainContactor = mainContactor)




    }
}