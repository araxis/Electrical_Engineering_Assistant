package com.vm.eea.application

import kotlin.math.acos
import kotlin.math.sin
import kotlin.math.tan

data class CosPhi(val value:Double){

    constructor(value:Number):this(value.toDouble())

    init {
        require(value in .1..1.0){"value not in range 0.1..1"}
    }

    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return value.format(pattern,empty)
    }


    fun phi()=  acos(value)
    fun sinPhi()= sin(phi())
    fun tanPhi()= tan(phi())
    operator fun compareTo(other: CosPhi): Int {
        if(value==other.value) return 0
        if(value>other.value) return 1
        return -1
    }

}


