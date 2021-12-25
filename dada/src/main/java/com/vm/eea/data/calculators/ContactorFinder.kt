package com.vm.eea.data.calculators

import com.vm.eea.application.A
import com.vm.eea.application.contactor.Contactor
import com.vm.eea.application.Current
import com.vm.eea.application.contactor.IContactorFinder
import com.vm.eea.application.protectionDevice.ProtectionDevice

class ContactorFinder: IContactorFinder {
    val data= listOf(9,12,18,25,32,40,50,65,80,95,115,150,185,225,265,330,400,500,630,800)
    override suspend fun withGreaterOrEqual(current: Current): Contactor? {
        val currentInAmpere=(current to Current.Unit.A).value
        val find= data.firstOrNull { it>=currentInAmpere } ?: return null
        return Contactor(find.A)
    }

    override suspend fun withGLess(current: Current): Contactor? {
        val currentInAmpere=(current to Current.Unit.A).value
        val find= data.firstOrNull { it < currentInAmpere } ?: return null
        return Contactor(find.A)
    }
}