package com.vm.eea.data.calculators

import com.vm.eea.application.KVAr
import com.vm.eea.application.ReactivePower
import com.vm.eea.application.capacitorContactor.CapacitorContactor
import com.vm.eea.application.capacitorContactor.ICapacitorContactorFinder

class CapacitorContactorFinder:ICapacitorContactorFinder {
    val data= listOf<Number>(12.5,17,25,34,60)
    override suspend fun withGreaterOrEqual(reactivePower: ReactivePower): CapacitorContactor? {
        val kVAr=(reactivePower to ReactivePower.Unit.KVAr).value
        val find= data.firstOrNull { it.toDouble() >= kVAr } ?: return null
       return CapacitorContactor(find.KVAr)
    }
}