package com.vm.eea.application.calculators

import com.vm.eea.application.*
import com.vm.eea.application.protectionDevice.ProtectionDeviceType
import com.vm.eea.application.calculators.CurrentCalculator
import com.vm.eea.application.protectionDevice.*

class PanelCircuitBreakerCalculator(
    private val acbFinder: IAcbFinder,
    private val tmbFinder: ITmbFinder,
    private val mccbFinder: IMccbFinder
) {
    suspend operator fun invoke(current: Current): ProtectionDeviceResult{


        if (current < 32.A ) {
            val tmb= tmbFinder(current)
            return ProtectionDeviceResult(tmb.toResult(),ProtectionDeviceType.Tmb)
        }
        val sparedCurrent = current * 1.1
        if (current < 1600.A) {
            val mccb= mccbFinder(sparedCurrent)
            return ProtectionDeviceResult(mccb.toResult(),ProtectionDeviceType.Mccb)
        }
        val acb= acbFinder(sparedCurrent)
        return ProtectionDeviceResult(acb.toResult(),ProtectionDeviceType.Acb)
    }


}