package com.vm.eea.data.calculators

import com.vm.eea.application.KW
import com.vm.eea.application.Power
import com.vm.eea.application.ssd.ISsdFinder
import com.vm.eea.application.ssd.Ssd

class SsdFinder: ISsdFinder {
    val data= listOf<Number>(1.1,3,4,5.5,7.5,11,15,18.5,22,30,37,45,55,75,90,
    110,132,160,220,250,315,355,400,500,630)
    override suspend fun withGreaterOrEqual(power: Power): Ssd? {
        val powerInKw=(power to Power.Unit.KW).value
        val find= data.firstOrNull { it.toDouble()>=powerInKw } ?: return null
        return Ssd(find.KW)
    }
}