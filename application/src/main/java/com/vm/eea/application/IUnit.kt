package com.vm.eea.application

interface IUnit {
    val symbol:String
    fun toBase(value:Double):Double
    fun fromBase(value: Double):Double
}