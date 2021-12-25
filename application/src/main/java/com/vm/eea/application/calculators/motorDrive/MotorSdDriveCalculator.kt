package com.vm.eea.application.calculators.motorDrive

import com.vm.eea.application.Current
import com.vm.eea.application.contactor.IContactorFinder
import com.vm.eea.application.toResult

class MotorSdDriveCalculator(
    private val contactorFinder: IContactorFinder
) {
    suspend operator fun invoke(
        current: Current,
        isBiDirect: Boolean
    ): IMotorPowerDriveResult {

        val mainContactorCurrent=current * .58
        val starContactorCurrent=current * .33
        val mainContactor = contactorFinder.withGreaterOrEqual(mainContactorCurrent)
        val starContactor=contactorFinder.withGreaterOrEqual(starContactorCurrent)

         if (isBiDirect) {
            return BiDirectSdDriveResult(mainContactor = mainContactor.toResult(),deltaContactor = mainContactor.toResult(),
                starContactor = starContactor.toResult(),mainContactor.toResult())
        }

        return SdDriveResult(mainContactor = mainContactor.toResult(),deltaContactor = mainContactor.toResult(),
        starContactor = starContactor.toResult())

    }
}