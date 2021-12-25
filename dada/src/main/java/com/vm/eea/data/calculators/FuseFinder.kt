package com.vm.eea.data.calculators

import com.vm.eea.application.A
import com.vm.eea.application.Current
import com.vm.eea.application.protectionDevice.ProtectionDevice
import com.vm.eea.application.protectionDevice.IFuseFinder

class FuseFinder: IFuseFinder {
    val data= listOf(2,4,6,10,20,25,32,40,50,63,80,100,125,160,200,250,400,630,800)
    override suspend fun withGreaterOrEqual(current: Current): ProtectionDevice.Fuse? {
        val currentInAmpere=(current to Current.Unit.A).value
        val find= data.firstOrNull { it>=currentInAmpere } ?: return null
        return ProtectionDevice.Fuse(find.A)
    }
}