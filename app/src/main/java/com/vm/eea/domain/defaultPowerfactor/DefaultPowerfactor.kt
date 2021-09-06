package com.vm.eea.domain.defaultPowerfactor

import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.PowerSystem

data class DefaultPowerfactor(
    val value: PowerFactor,
    val system: PowerSystem,
    val isCustom: Boolean,
    val id: Long = 0,
)
