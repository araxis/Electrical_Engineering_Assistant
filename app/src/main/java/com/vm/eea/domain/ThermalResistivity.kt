package com.vm.eea.domain

import com.vm.eea.utilities.format

data class ThermalResistivity(val value:Double,val unit:UnitOfThermalResistivity){
    constructor(value:Number, unitOfThermalResistivity: UnitOfThermalResistivity):this(value.toDouble(),unitOfThermalResistivity)

    override fun toString()="${value.format()} ${unit.name}"
    operator fun invoke() =toString()
}

enum class UnitOfThermalResistivity {
MW;
    operator fun invoke()=name

}
