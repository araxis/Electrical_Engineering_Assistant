package com.vm.eea.application

data class Frequency(val value:Double,val unit: UnitOfFrequency){
    constructor(value:Number, unitOfFrequency: UnitOfFrequency):this(value.toDouble(),unitOfFrequency)

    fun toString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }
}

enum class UnitOfFrequency{
 Hz,KHz
}
