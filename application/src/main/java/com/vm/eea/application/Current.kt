package com.vm.eea.application

import kotlin.math.pow

data class Current(override val value:Double, override val unit: Unit):IQuantity<Current.Unit>{

    constructor(value:Number,unit: Unit):this(value.toDouble(),unit)


    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

    infix fun to(newUnit: Unit): Current {
        val baseValue=unit.toBase(value)
        val newValue=newUnit.fromBase(baseValue)
        return Current(newValue,newUnit)
    }

    fun pow2()=this.copy(value= value.pow(2.0),unit=unit)

    fun toBase(): Current {
        val baseValue= Unit.A.toBase(value)
        return Current(baseValue, Unit.A)
    }

    operator fun compareTo(other: Current): Int {
        val otherInThisUnit=other to unit
        if(value==otherInThisUnit.value) return 0
        if(value>otherInThisUnit.value) return 1
        return -1
    }

    operator fun times(conf: Double): Current {
        return Current(value*conf,unit)
    }

    operator fun div(divBy: Double): Current {
        return Current(value/divBy,unit)
    }

    enum class Unit: IUnit {
        A {
            override val symbol ="A"
            override fun toBase(value: Double)=value

            override fun fromBase(value: Double)=value
        },KA {
            override val symbol ="kA"
            override fun toBase(value: Double)=value * 1000

            override fun fromBase(value: Double)=value/1000
        };

        operator fun invoke()=this.name
    }
}

