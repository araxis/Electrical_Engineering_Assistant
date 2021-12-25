package com.vm.eea.application

data class SlipFactor(val value:Double){

    constructor(value:Number):this(value.toDouble())
    init {
        require(value in 0.0..100.0){"value must be in range 1..100"}
    }

    fun zeroToOne()=value/100

    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return value.format(pattern,empty)
    }


    operator fun compareTo(other: SlipFactor): Int {
        if(value==other.value) return 0
        if(value>other.value) return 1
        return -1
    }



}


