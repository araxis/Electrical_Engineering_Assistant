package com.vm.eea.data.calculators

import com.vm.eea.application.KVAr
import com.vm.eea.application.ReactivePower
import com.vm.eea.application.capacitor.Capacitor
import com.vm.eea.application.capacitor.ICapacitorFinder

class CapacitorFinder:ICapacitorFinder {
    val data= listOf<Number>(2.5,5,6.25,7.5,10,12.5,15,20,25,30,50)
    override suspend fun withGreaterOrEqual(reactivePower: ReactivePower): Capacitor? {
        val kVAr=(reactivePower to ReactivePower.Unit.KVAr).value
        val find= data.firstOrNull { it.toDouble() >= kVAr } ?: return null
        return Capacitor(find.KVAr)
    }
}