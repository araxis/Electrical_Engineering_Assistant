package com.vm.eea.application


data class Power(val value:Double,val unit: Unit){



    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

 operator fun compareTo(other: Power): Int {
       return compareValuesBy(this,other, Power::value, Power::unit)
}

    infix fun to(newUnit: Unit): Power {
     val baseValue=unit.toBase(value)
     val newValue=newUnit.fromBase(baseValue)
     return Power(newValue,newUnit)
    }

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

    override fun toBase(value:Double) =value
    override fun fromBase(value: Double) =value
   },
    KW{
        override  fun toBase(value:Double) =value*1000
        override  fun fromBase(value:Double) =value/1000
   },
    HP {
        override fun toBase(value: Double) = value * 745.7

        override fun fromBase(value: Double) = value / 745.7
    };

  operator fun invoke()=this.name
 }


}


