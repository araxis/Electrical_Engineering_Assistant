package com.vm.eea.domain

data class Efficiency(val value:Double){
    constructor(value:Number):this(value.toDouble())

    override fun toString()="${value.format()} %"
    operator fun invoke()=toString()
}
