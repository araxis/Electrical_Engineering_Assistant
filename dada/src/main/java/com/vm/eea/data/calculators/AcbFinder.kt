package com.vm.eea.data.calculators

import com.vm.eea.application.A
import com.vm.eea.application.protectionDevice.ProtectionDevice
import com.vm.eea.application.Current
import com.vm.eea.application.protectionDevice.IAcbFinder

class AcbFinder: IAcbFinder {

     private val currents= listOf(800.0,1000.0,1250.0,1600.0,2000.0,2500.0,3200.0)

    override suspend fun invoke(current: Current): ProtectionDevice.Acb? {
        val currentInAmpere=(current to Current.Unit.A).value
        val find= currents.firstOrNull { it>=currentInAmpere } ?: return null
        return ProtectionDevice.Acb(find.A)
    }
}