package com.vm.eea.application.protectionDevice

import com.vm.eea.application.Current

interface ITmbFinder {

    suspend operator fun invoke(current: Current): ProtectionDevice.Tmb?
}