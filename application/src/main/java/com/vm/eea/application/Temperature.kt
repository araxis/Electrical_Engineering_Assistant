package com.vm.eea.application

data class Temperature(val value:Double,val unit: UnitOfTemperature){

    constructor(value:Number, unit: UnitOfTemperature):this(value.toDouble(),unit)
    override fun toString()="${value.format()} ${unit.name}"
    operator fun invoke()=toString()

    fun toString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }
}

enum class UnitOfTemperature {
    C ,F;

    operator fun invoke() =name

}
