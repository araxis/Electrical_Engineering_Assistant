package com.vm.eea.application

import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow

data class ReactivePower(override val value:Double, override val unit: Unit):IQuantity<ReactivePower.Unit> {

    constructor(value:Number,unit: Unit):this(value.toDouble(),unit)

    operator fun compareTo(other: ReactivePower): Int {
        val thisBase= (to(Unit.VAr)).value
        val otherBase=(other to Unit.VAr).value
        if(otherBase ==thisBase) return 0
        if(thisBase < otherBase) return -1
        return 1

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

    operator fun div(apparentPower: ApparentPower): CosPhi {
        if(apparentPower==0.VA) return CosPhi(0)
        val base=to(Unit.VAr).value
        val baseApparent= (apparentPower to ApparentPower.Unit.VA).value
        val value=base / baseApparent
        return CosPhi(cos(asin(value)))
    }

    operator fun div(activePower: Power): CosPhi {
        if(activePower==0.W) return CosPhi(0)
        val base=to(Unit.VAr).value
        val basePower= (activePower to Power.Unit.W).value
        val tanPgi=base / basePower
        return CosPhi(cos(atan(tanPgi)))
    }

    enum class Unit : IUnit {


        VAr{
            override val symbol ="VAr"
            override fun toBase(value: Double) = value

            override fun fromBase(value: Double) = value
        },
        KVAr{
            override val symbol ="kVAr"
            override fun toBase(value: Double) = value * 1000

            override fun fromBase(value: Double) = value / 1000
        }
    }
}