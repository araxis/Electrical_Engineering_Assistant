package com.vm.eea.application.protectionDevice

import com.vm.eea.application.Current

sealed class ProtectionDevice{
     abstract val type:ProtectionDeviceType
     data class Tmb(val min: Current, val max: Current): ProtectionDevice() {
        override val type=ProtectionDeviceType.Tmb
    }

    data class Mccb(val current: Current): ProtectionDevice()
    {
        override val type=ProtectionDeviceType.Mccb
    }
    data class Acb(val current: Current): ProtectionDevice(){
        override val type=ProtectionDeviceType.Acb
    }
    data class Fuse(val current: Current): ProtectionDevice(){
        override val type=ProtectionDeviceType.Fuse
    }

}
