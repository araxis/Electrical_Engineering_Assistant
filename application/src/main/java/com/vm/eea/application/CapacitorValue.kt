package com.vm.eea.application


data class CapacitorValue(override val value:Double, override val unit: Unit):IQuantity<CapacitorValue.Unit> {

    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

 operator fun compareTo(other: CapacitorValue): Int {
       return compareValuesBy(this,other, CapacitorValue::value, CapacitorValue::unit)
}

    infix fun to(newUnit: Unit): CapacitorValue {
     val baseValue=unit.toBase(value)
     val newValue=newUnit.fromBase(baseValue)
     return CapacitorValue(newValue,newUnit)
    }


enum class Unit: IUnit {
    F{

        override val symbol ="F"
    override fun toBase(value:Double) =value
    override fun fromBase(value: Double) =value
   },
    UF{
        override val symbol ="uF"
        override  fun toBase(value:Double) =value*1000
        override  fun fromBase(value:Double) =value/1000
   };

  operator fun invoke()=this.name
 }


}


