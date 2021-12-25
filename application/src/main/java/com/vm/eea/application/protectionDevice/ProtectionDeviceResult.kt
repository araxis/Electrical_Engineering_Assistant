package com.vm.eea.application.protectionDevice

import com.vm.eea.application.ItemResult

data class ProtectionDeviceResult(val protection:ItemResult<ProtectionDevice>,val keyType: ProtectionDeviceType)