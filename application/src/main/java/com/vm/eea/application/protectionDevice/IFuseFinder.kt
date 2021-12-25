package com.vm.eea.application.protectionDevice

import com.vm.eea.application.Current
import com.vm.eea.application.protectionDevice.ProtectionDevice

interface IFuseFinder {
    suspend  fun withGreaterOrEqual(current: Current): ProtectionDevice.Fuse?
}