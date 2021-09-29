package com.vm.eea.application

data class DefaultPowerfactor(
    val value: CosPhi,
    val system: PowerSystem,
    val isCustom: Boolean,
    val id: Long = 0,
)
