package com.vm.eea.domain

import com.vm.eea.utilities.format

data class WireSize(val value:Double, val unit:UnitOfWireSize){

  constructor(value:Number,unit: UnitOfWireSize):this(value.toDouble(),unit)

  override fun toString() ="${value.format()} ${unit()}"
  operator fun invoke() =toString()
}

enum class UnitOfWireSize {
 MM2,AWG,KCMIL;
    operator fun invoke()=name
}
