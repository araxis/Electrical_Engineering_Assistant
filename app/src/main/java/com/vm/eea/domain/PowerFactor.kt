package com.vm.eea.domain

import com.vm.eea.utilities.format

data class PowerFactor(val value:Double){
    constructor(value:Number):this(value.toDouble())

    override fun toString() = value.format()
    operator fun invoke() =toString()
}