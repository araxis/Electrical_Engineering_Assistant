package com.vm.eea.application.calculators

import com.vm.eea.application.*
import com.vm.eea.application.protectionDevice.ProtectionDeviceType
import com.vm.eea.application.calculators.CurrentCalculator
import com.vm.eea.application.protectionDevice.*

class MotorCircuitBreakerCalculator(
    private val currentCalculator: CurrentCalculator,
    private val acbFinder: IAcbFinder,
    private val tmbFinder: ITmbFinder,
    private val mccbFinder: IMccbFinder,
    private val fuseFinder: IFuseFinder
) {
    suspend operator fun invoke(current: Current,
                                protectionType: ProtectionType,
                                hasOverLoadProtection:Boolean): ProtectionDeviceResult{
        if(protectionType== ProtectionType.Fuse){
            val ratedCurrent=current * 1.75
            val fuse=fuseFinder.withGreaterOrEqual(ratedCurrent)
            return ProtectionDeviceResult(fuse.toResult(),ProtectionDeviceType.Fuse)
        }

        if (current < 63.A && !hasOverLoadProtection) {
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


  suspend operator fun invoke(
      voltage: Voltage,
      power: Power,
      system: PowerSystem,
      cosPhi: CosPhi,
      efficiency: Efficiency,
      protectionType: ProtectionType,
      hasOverLoadProtection:Boolean
  ): ProtectionDeviceResult {
      val current=currentCalculator(voltage, power, system, cosPhi, efficiency)
      return invoke(current,protectionType,hasOverLoadProtection)
  }
}