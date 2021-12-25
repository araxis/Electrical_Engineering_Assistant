package com.vm.eea.application

data class Speed(val value:Double) {
    constructor(value:Number):this(value.toDouble())

    operator fun minus(other:Speed):Speed{
        return Speed(value-other.value)
    }

    operator fun div(other: Speed):Speed{
        return Speed(value/other.value)
    }

    operator fun compareTo(other: Speed): Int {
        if(other.value==value) return 0
        if(value>other.value) return 1
        return -1
    }
}