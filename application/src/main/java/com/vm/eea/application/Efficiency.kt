package com.vm.eea.application

data class Efficiency(val value:Double){
    constructor(value:Number):this(value.toDouble())



    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} %"
    }
    fun percentage()=value/100
}
