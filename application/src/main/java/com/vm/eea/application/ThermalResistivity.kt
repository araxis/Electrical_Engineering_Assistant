package com.vm.eea.application

data class ThermalResistivity(val value:Double,val unit: UnitOfThermalResistivity){
    constructor(value:Number, unitOfThermalResistivity: UnitOfThermalResistivity):this(value.toDouble(),unitOfThermalResistivity)

    override fun toString()="${value.format()} ${unit.name}"
    operator fun invoke() =toString()

    fun toString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }
}

enum class UnitOfThermalResistivity {
MW;
    operator fun invoke()=name

}
