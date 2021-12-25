package com.vm.eea.data.calculators

import com.vm.eea.application.KW
import com.vm.eea.application.Power
import com.vm.eea.application.vsd.Vsd
import com.vm.eea.application.vsd.IVsdFinder

class VsdFinder: IVsdFinder {
    val data= listOf<Number>(.37,.55,.75,1.1,1.5,2.2,3,4,5.5,7.5,11,15,18.5,22,
    30,37,45,55,75,90,110,130,160,220,250,315,400,500,630)
    override suspend fun withGreaterOrEqual(power: Power): Vsd? {
        val powerInKw=(power to Power.Unit.KW).value
        val find= data.firstOrNull { it.toDouble()>=powerInKw } ?: return null
        return Vsd(find.KW)
    }
}