package com.vm.eea.application

data class WireSize(val value:Double, val unit: UnitOfWireSize){

  constructor(value:Number,unit: UnitOfWireSize):this(value.toDouble(),unit)




  override fun toString() ="${value.format()} ${unit()}"
  operator fun invoke() =toString()

    fun toString(pattern:String="###.###",empty:String=""):String{
        return "${value.format(pattern,empty)} ${unit.name}"
    }
}

enum class UnitOfWireSize {
 MM2,AWG,KCMIL;
    operator fun invoke()=name
}
