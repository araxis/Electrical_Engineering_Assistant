package com.vm.eea.utilities

import java.lang.IllegalArgumentException


interface Mapper<T, R> {
    operator fun invoke(param: T): R
}
interface IGuardClause
class Guard:IGuardClause{
    companion object{
        val against=Guard()
    }
}


fun <T:Any> IGuardClause.nullValue(input:T?,parameterName:String,message:String?=null):T{
    if (input == null) throw IllegalArgumentException(message?:"Required input $parameterName was empty.")
    return input
}

fun IGuardClause.nullOrBlank(input:String?,parameterName:String,message:String?=null):String{

    if (input.isNullOrBlank()) throw IllegalArgumentException(message?:"Required input $parameterName was null.")
    return input
}

fun IGuardClause.blank(input:String,parameterName:String,message:String?=null):String{
    if (input.isBlank()) throw IllegalArgumentException(message?:"Required input $parameterName was empty.")
    return input
}

fun IGuardClause.outOfRange(input:Double,rangeFrom:Double,rangeTo:Double,parameterName:String,message:String?=null):Double{
    if (input !in rangeFrom..rangeTo) throw IllegalArgumentException(message?:"Required input $parameterName was empty.")
    return input
}