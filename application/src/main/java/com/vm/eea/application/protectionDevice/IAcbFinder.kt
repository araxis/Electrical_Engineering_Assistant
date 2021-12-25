package com.vm.eea.application.protectionDevice

import com.vm.eea.application.protectionDevice.ProtectionDevice
import com.vm.eea.application.Current

interface IAcbFinder {
    suspend operator fun invoke(current: Current): ProtectionDevice.Acb?
}