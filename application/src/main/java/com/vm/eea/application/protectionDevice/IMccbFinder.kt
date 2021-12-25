package com.vm.eea.application.protectionDevice

import com.vm.eea.application.protectionDevice.ProtectionDevice
import com.vm.eea.application.Current

interface IMccbFinder {
    suspend operator fun invoke(current: Current): ProtectionDevice.Mccb?
}