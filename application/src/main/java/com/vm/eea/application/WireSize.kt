package com.vm.eea.application

data class WireSize(override val value:Double, override val unit: UnitOfWireSize):IQuantity<UnitOfWireSize>{

  constructor(value:Number,unit: UnitOfWireSize):this(value.toDouble(),unit)




  override fun toString() ="${value.format()} ${unit()}"
  operator fun invoke() =toString()

    fun toString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }
}

enum class UnitOfWireSize:IUnit {
 MM2 {
     override val symbol ="mm2"
     override fun toBase(value: Double): Double {
         TODO("Not yet implemented")
     }

     override fun fromBase(value: Double): Double {
         TODO("Not yet implemented")
     }
 },AWG {
        override val symbol ="awg"
        override fun toBase(value: Double): Double {
            TODO("Not yet implemented")
        }

        override fun fromBase(value: Double): Double {
            TODO("Not yet implemented")
        }
    },KCMIL {
        override val symbol ="kcmil"
        override fun toBase(value: Double): Double {
            TODO("Not yet implemented")
        }

        override fun fromBase(value: Double): Double {
            TODO("Not yet implemented")
        }
    };
    operator fun invoke()=name
}
