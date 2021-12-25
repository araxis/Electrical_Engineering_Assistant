package com.vm.eea.data.calculators

import com.vm.eea.application.A
import com.vm.eea.application.bimetal.Bimetal
import com.vm.eea.application.Current
import com.vm.eea.application.bimetal.IBimetalFinder

class BimetalFinder: IBimetalFinder {
    private val currents= listOf<Pair<Number,Number>>(.16 to .25,.25 to .4,0.4 to .63,.63 to 1,
        1 to 1.63,1.63 to 2.5,2.5 to 4,4 to 6,5.5 to 8,7 to 10,9 to 13,12 to 18,17 to 25,23 to 32,
        30 to 38,30 to 40,37 to 50,48 to 65,55 to 70,63 to 80,60 to 100,80 to 104,90 to 150,130 to 220 ,
    200 to 330,300 to 500,380 to 630)
    override suspend fun invoke(current: Current): Bimetal? {
        val currentInAmpere=(current to Current.Unit.A).value
        val pair= currents.firstOrNull { currentInAmpere in it.first.toDouble() .. it.second.toDouble() }?: return null
        return Bimetal(pair.first.A,pair.second.A)

    }
}