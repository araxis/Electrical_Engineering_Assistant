package com.vm.eea.domain

import com.vm.eea.utilities.format

data class Temperature(val value:Double,val unit:UnitOfTemperature){

    constructor(value:Number, unit: UnitOfTemperature):this(value.toDouble(),unit)
    override fun toString()="${value.format()} ${unit.name}"
    operator fun invoke()=toString()
}

enum class UnitOfTemperature {
    C ,F;

    operator fun invoke() =name

}
