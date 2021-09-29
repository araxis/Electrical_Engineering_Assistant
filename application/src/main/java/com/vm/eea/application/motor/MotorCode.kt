package com.vm.eea.application.motor

@JvmInline
value class MotorCode(val value :String) {

    init {
        require(value.isNotBlank()){"value must be not empty"}
    }
}