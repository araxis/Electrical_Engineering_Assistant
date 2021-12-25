package com.vm.eea.data.calculators

import com.vm.eea.application.A
import com.vm.eea.application.protectionDevice.ProtectionDevice
import com.vm.eea.application.Current
import com.vm.eea.application.protectionDevice.ITmbFinder

class TmbFinder: ITmbFinder {

    private val currents= listOf(.1 to .16,.16 to .25,.25 to .4,0.4 to .63,.63 to 1.0,
    1.0 to 1.6,1.6 to 2.5,2.5 to 4.0,4.0 to 6.3,6.0 to 10.0,9.0 to 14.0,13.0 to 18.0,
    17.0 to 23.0,20.0 to 25.0,24.0 to 32.0,30.0 to 40.0,37.0 to 50.0,48.0 to 65.0,56.0 to 80.0)
    override suspend fun invoke(current: Current): ProtectionDevice.Tmb? {
        val currentInAmpere=(current to Current.Unit.A).value
        val find= currents.firstOrNull { it.second >currentInAmpere } ?:return null
        return ProtectionDevice.Tmb(find.first.A,find.second.A)
    }
}