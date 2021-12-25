package com.vm.eea.utilities

import com.vm.eea.application.*
import com.vm.eea.application.bimetal.Bimetal
import com.vm.eea.application.bimetal.BimetalResult
import com.vm.eea.application.calculators.CosPhiResult
import com.vm.eea.application.calculators.EfficiencyResult
import com.vm.eea.application.panel.CoincidenceFactor
import com.vm.eea.application.protectionDevice.ProtectionDevice
import com.vm.eea.application.protectionDevice.ProtectionDeviceResult
import com.vm.eea.application.protectionDevice.ProtectionDeviceType
import com.vm.eea.ui.INavigationCommand
import com.vm.eea.ui.PropertyItem

//fun<T:IUnit>  IQuantity<T>.displayOrZero(pattern:String="###.###"):String{
//    return "${input.format(pattern,"0")} ${unit.symbol}"
//}

fun Current.displayOrZero(pattern:String="###.##"):String {
 val displayValue= if(this >=1.KA){
      this to Current.Unit.KA
  }else{
      this
  }
    return "${displayValue.value.format(pattern,"0")} ${displayValue.unit.symbol}"
}

fun Power.displayOrZero(pattern:String="###.##"):String {
    val displayValue= if(this >=1.KW){
        this to Power.Unit.KW
    }else{
        this
    }
    return "${displayValue.value.format(pattern,"0")} ${displayValue.unit.symbol}"
}

fun ReactivePower.displayOrZero(pattern:String="###.##"):String {
    val displayValue= if(this >=1.KVAr){
        this to ReactivePower.Unit.KVAr
    }else{
        this
    }
    return "${displayValue.value.format(pattern,"0")} ${displayValue.unit.symbol}"
}

fun ApparentPower.displayOrZero(pattern:String="###.##"):String {
    val displayValue= if(this >=1.KVA){
        this to ApparentPower.Unit.KVA
    }else{
        this
    }
    return "${displayValue.value.format(pattern,"0")} ${displayValue.unit.symbol}"
}

fun Torque.displayOrZero(pattern:String="###.##"):String{
    return "${value.format(pattern,"0")} N.m"
}

fun Speed.displayOrZero(pattern:String="###.##"):String{
    return "${value.format(pattern,"0")} RPM"
}

fun SlipFactor.displayOrZero(pattern:String="###.##"):String{
    return "${value.format(pattern,"0")} %"
}

fun CosPhi.displayOrZero(pattern:String="###.##"):String{
    return value.format(pattern,"0")
}

fun CoincidenceFactor.displayOrZero(pattern:String="###.##"):String{
    return value.format(pattern,"0")
}

fun Efficiency.displayOrZero(pattern:String="###.##"):String{
    return "${value.format(pattern,"0")} %"
}

fun Voltage.displayOrZero(pattern:String="###.##"):String{
    return "${value.format(pattern,"0")} ${unit.name}"
}

fun Bimetal.displayRange(pattern:String="###.###"):String{
    return "${min.toFormatString(pattern)} - ${max.toFormatString(pattern)}"
}



fun<T>  ItemResult<T>.display(foundString:(T)->String,notFoundString:String="Not found"):String{
    return when(this){
        is ItemResult.Found -> foundString(this.value)
        is ItemResult.NotFound -> notFoundString
    }
}

fun CosPhiResult.display(pattern:String="###.##"):String{
  return  when(this){
        CosPhiResult.Error -> "Not valid Result"
        is CosPhiResult.Ready -> value.displayOrZero(pattern)
    }
}

fun EfficiencyResult.display(pattern:String="###.##"):String{
    return  when(this){
        EfficiencyResult.Error -> "Not valid Result"
        is EfficiencyResult.Ready -> value.displayOrZero(pattern)
    }
}

fun BimetalResult.display(pattern:String="###.##"):String{
    return when(this){
        is BimetalResult.Use -> this.part.display({it.displayRange(pattern)})
        BimetalResult.UseLess -> "Not required"
    }

}

fun ProtectionDevice.display(pattern:String="###.##"):String{
    return when(this){
        is ProtectionDevice.Acb -> "Acb: ${current.toFormatString(pattern)}"
        is ProtectionDevice.Fuse -> "Fuse: ${current.toFormatString(pattern)}"
        is ProtectionDevice.Mccb -> "Mccb: ${current.toFormatString(pattern)}"
        is ProtectionDevice.Tmb -> "Tmb: ${min.toFormatString(pattern)} - ${max.toFormatString(pattern)}"
    }
}

fun ProtectionDeviceType.display():String = name



fun Current.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,displayOrZero(),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun Power.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,displayOrZero(),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun Voltage.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,displayOrZero(),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun Efficiency.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,displayOrZero(),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun StartMode.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,this.name,
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun PowerSystem.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,this.name,
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun ApparentPower.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,displayOrZero(),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}
fun ReactivePower.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,displayOrZero(),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun Torque.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,displayOrZero(),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun Speed.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,displayOrZero(),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun CosPhi.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,value.format(empty = "0"),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun CoincidenceFactor.propertyItem(name:String,category:String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,value.format(empty = "0"),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}

fun ProtectionDeviceResult.display():String{

    return when(val key=this.protection){
        is ItemResult.Found ->  key.value.display()
        is ItemResult.NotFound ->"Not found(${this.keyType.display()})"
    }
}

fun ProtectionDeviceResult.propertyItem(name:String, category:String, updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

  val display=  when(val key=this.protection){
        is ItemResult.Found ->  key.value.display()
        is ItemResult.NotFound ->"Not found(${this.keyType.display()})"
    }

    return PropertyItem(name,display,
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}


fun<T> ItemResult<T>.propertyItem(name:String, category:String, foundString:(T)->String,updateViewRoute:(()-> INavigationCommand)? =null):PropertyItem{

    return PropertyItem(name,display(foundString =foundString),
        visible = true,
        isCalculated = false,
        category = category,
        updateViewRoute = updateViewRoute)
}






