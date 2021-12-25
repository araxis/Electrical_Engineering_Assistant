package com.vm.eea.application

import kotlin.math.pow

data class ApparentPower(override val value:Double, override val unit: Unit):IQuantity<ApparentPower.Unit> {

    constructor(value:Number,unit: Unit):this(value.toDouble(),unit)

    operator fun compareTo(other: ApparentPower): Int {
        return compareValuesBy(this,other, ApparentPower::value, ApparentPower::unit)
    }

    infix fun to(newUnit: Unit): ApparentPower {
        val baseValue= unit.toBase(value)
        val newValue=newUnit.fromBase(baseValue)
        return ApparentPower(newValue,newUnit)
    }

    fun pow2()=this.copy(value= value.pow(2.0),unit=unit)


    fun toFormatString(pattern:String="###.###", empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

    fun toBase(): ApparentPower {
        val baseValue= Unit.VA.toBase(value)
        return ApparentPower(baseValue, Unit.VA)
    }

    operator fun times(conf:Number)= ApparentPower(value*conf.toDouble(),unit)

    companion object{
        fun toBase(value:Number)= ApparentPower(value.toDouble(), Unit.VA)
    }




    enum class Unit : IUnit {

        VA {
            override val symbol ="VA"

            override fun toBase(value: Double) = value

            override fun fromBase(value: Double) = value
        },
        KVA{
            override val symbol ="VA"
            override fun toBase(value: Double) = value * 1000

            override fun fromBase(value: Double) = value / 1000
        }
    }

}