package com.vm.eea.application

import kotlin.math.pow

data class ReactivePower(val value:Double,val unit: Unit) {

    constructor(value:Number,unit: Unit):this(value.toDouble(),unit)

    operator fun compareTo(other: ReactivePower): Int {
        return compareValuesBy(this,other, ReactivePower::value, ReactivePower::unit)
    }

    infix fun to(newUnit: Unit): ReactivePower {
        val baseValue=unit.toBase(value)
        val newValue=newUnit.fromBase(baseValue)
        return ReactivePower(newValue,newUnit)
    }

    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

    fun pow2()= ReactivePower(value.pow(2),unit)

    fun toBase(): ReactivePower {
        val baseValue= Unit.VAr.toBase(value)
        return ReactivePower(baseValue, Unit.VAr)
    }

    companion object{
        fun toBase(value:Number)= ReactivePower(value.toDouble(), Unit.VAr)
    }

    enum class Unit : IUnit {


        VAr{
            override fun toBase(value: Double) = value

            override fun fromBase(value: Double) = value
        },
        KVAr{
            override fun toBase(value: Double) = value * 1000

            override fun fromBase(value: Double) = value / 1000
        }
    }
}