package com.vm.eea.domain

data class Frequency(val value:Double,val unitOfFrequency: UnitOfFrequency){
    constructor(value:Number, unitOfFrequency: UnitOfFrequency):this(value.toDouble(),unitOfFrequency)
}

enum class UnitOfFrequency{
 Hz,KHz
}
