package com.vm.eea.domain

data class Power(val value:Double,val unit: UnitOfPower){

 override fun toString()="${value.format()} ${unit.invoke()}"
 operator fun invoke()=toString()
}

enum class UnitOfPower {
 W,KW;

 operator fun invoke()=this.name
}
