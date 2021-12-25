package com.vm.eea.data.calculators

import com.vm.eea.application.A
import com.vm.eea.application.protectionDevice.ProtectionDevice
import com.vm.eea.application.Current
import com.vm.eea.application.protectionDevice.IMccbFinder

class MccbFinder: IMccbFinder {
    private val currents= listOf(16.0,25.0,32.0,40.0,50.0,
    63.0,80.0,100.0,125.0,160.0,200.0,250.0,
    400.0,630.0,800.0,1000.0,1250.0,1600.0)

    override suspend fun invoke(current: Current): ProtectionDevice.Mccb? {
        val currentInAmpere=(current to Current.Unit.A).value
        val find= currents.firstOrNull { it>=currentInAmpere } ?: return null
        return ProtectionDevice.Mccb(find.A)
    }
}