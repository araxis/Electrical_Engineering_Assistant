package com.vm.eea.application

interface IQuantity<T:IUnit> {
    val value:Double
    val unit: T
}