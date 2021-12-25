package com.vm.eea.application

data class Length(override val value:Double, override val unit: Unit):IQuantity<Length.Unit>{
    constructor(value:Number,unit: Unit):this(value.toDouble(),unit)
    init {
        require(value>=0){"value must be positive"}
    }


    fun toFormatString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }

    enum class Unit:IUnit{
        M {
            override val symbol="m"

            override fun toBase(value: Double): Double {
                TODO("Not yet implemented")
            }

            override fun fromBase(value: Double): Double {
                TODO("Not yet implemented")
            }
        },KM {
            override val symbol="m"
            override fun toBase(value: Double): Double {
                TODO("Not yet implemented")
            }

            override fun fromBase(value: Double): Double {
                TODO("Not yet implemented")
            }
        };

        operator fun invoke() =name
    }

}


