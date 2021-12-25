package com.vm.eea.utilities

import android.content.Context
import androidx.annotation.StringRes


interface IText{
    fun text(context:Context):String

    companion object{
        fun simple(text:String) =object :IText{
            override fun text(context: Context)=text
        }
        fun res(@StringRes resId: Int) =object :IText{
            override fun text(context: Context)=context.getString(resId)
        }
    }
}

data class SimpleText(val message:String):IText {
    override fun text(context: Context) =message
}
data class ResText(@StringRes val resId: Int):IText{
    override fun text(context: Context): String {
      return  context.getString(resId)
    }

}



data class ValidationError(val message:String):IText{
    override fun text(context: Context): String {
        return message
    }

}
interface IValidationClause

infix fun <T> Boolean.then(param: () -> T): T? = if (this) param() else null
infix fun <T> Boolean.then(param: T): T? = if (this) param else null

class Validator: IValidationClause {
    companion object{
        val validate= Validator()
    }
}


fun IValidationClause.notNullOrBlank(value:String?, message:String): ValidationError?{
    if(value.isNullOrBlank())return ValidationError(message)
    return null
}

fun IValidationClause.positiveNumber(value:String?, message:String): ValidationError?{
    val number=value?.toDoubleOrNull() ?: return ValidationError(message)
    if(number<=0)  return ValidationError(message)
    return null
}

fun IValidationClause.smallerOrEqual(value:String?,other:Double, message:String): ValidationError?{
    val number=value?.toDoubleOrNull() ?: return ValidationError(message)
    if(number>other)  return ValidationError(message)
    return null
}

fun IValidationClause.inRange(value:String?, from:Double, to:Double, errorMessage:String): ValidationError?{
    val number= value?.toDoubleOrNull() ?: return ValidationError(errorMessage)
    if(number !in from..to) return ValidationError(errorMessage)
    return null
}

fun IValidationClause.inRange(number:Double, from:Double, to:Double, errorMessage:String): ValidationError?{

    if(number !in from..to) return ValidationError(errorMessage)
    return null
}

val ValidationError?.isValid:Boolean
        get() = this==null

val ValidationError?.isNotValid:Boolean
    get() = this!=null