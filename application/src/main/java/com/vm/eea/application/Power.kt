package com.vm.eea.application

import kotlin.math.pow


data class Power(override val value:Double, override val unit: Unit):IQuantity<Power.Unit>{



    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

 operator fun compareTo(other: Power): Int {
     val thisBase= (to(Unit.W)).value
     val otherBase=(other to Unit.W).value
     if(otherBase ==thisBase) return 0
     if(thisBase < otherBase) return -1
     return 1
}

    infix fun to(newUnit: Unit): Power {
     val baseValue=unit.toBase(value)
     val newValue=newUnit.fromBase(baseValue)
     return Power(newValue,newUnit)
    }
    fun pow2()=this.copy(value= value.pow(2.0),unit=unit)

    operator fun times(conf:Number)= Power(value*conf.toDouble(),unit)

    operator fun div(apparentPower: ApparentPower): CosPhi {
        if(apparentPower==0.VA) return CosPhi(0)
        val basePower=to(Unit.W).value
        val baseApparent= (apparentPower to ApparentPower.Unit.VA).value
        val value=basePower / baseApparent
        return CosPhi(value)
    }


    companion object{
        fun toBase(value:Number)= Power(value.toDouble(), Unit.W)
    }


enum class Unit: IUnit {
    W{
        override val symbol ="w"
    override fun toBase(value:Double) =value
    override fun fromBase(value: Double) =value
   },
    KW{
        override val symbol ="kw"
        override  fun toBase(value:Double) =value*1000
        override  fun fromBase(value:Double) =value/1000
   },
    HP {
        override val symbol ="hp"
        override fun toBase(value: Double) = value * 745.7

        override fun fromBase(value: Double) = value / 745.7
    };

  operator fun invoke()=this.symbol
 }


}


