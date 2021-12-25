package com.vm.eea.application

data class Frequency(override val value:Double, override val unit: Unit):IQuantity<Frequency.Unit>{
    constructor(value:Number, unit: Unit):this(value.toDouble(),unit)

    infix fun to(newUnit: Unit): Frequency {
        val baseValue=unit.toBase(value)
        val newValue=newUnit.fromBase(baseValue)
        return Frequency(newValue,newUnit)
    }

    fun toString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

    enum class Unit:IUnit{
        Hz {
            override val symbol ="hz"
            override fun toBase(value:Double) =value
            override fun fromBase(value: Double) =value
        },KHz {
            override val symbol ="khz"
            override  fun toBase(value:Double) =value*1000
            override  fun fromBase(value:Double) =value/1000
        }
    }
}


