package com.vm.eea.application

import com.vm.eea.application.capacitor.Capacitor
import com.vm.eea.application.capacitorContactor.CapacitorContactor
import com.vm.eea.application.contactor.Contactor
import com.vm.eea.application.protectionDevice.ProtectionDevice
import com.vm.eea.application.ssd.Ssd
import com.vm.eea.application.vsd.Vsd

typealias ContactorResult=ItemResult<Contactor>
typealias VsdResult=ItemResult<Vsd>
typealias SsdResult=ItemResult<Ssd>
typealias CapacitorResult=ItemResult<Capacitor>
typealias CapacitorContactorResult=ItemResult<CapacitorContactor>
typealias FuseResult=ItemResult<ProtectionDevice.Fuse>

fun<T> T?.toResult(): ItemResult<T> {
    if(this==null) return ItemResult.NotFound()
    return ItemResult.Found(this)
}






