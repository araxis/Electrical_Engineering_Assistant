package com.vm.eea.application

data class Efficiency(val value:Double){
    constructor(value:Number):this(value.toDouble())


    init {
        require(value in 0.0..100.0){"value not in range 0..100"}
    }

    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} %"
    }
    fun percentage()=value/100


}
