package com.vm.eea.utilities

import com.vm.eea.application.SelectableItem

inline fun<reified T:Enum<T>> toSelectableList(selected:T?=null):List<SelectableItem<T>>{

    return  enumValues<T>().map { SelectableItem(it,isSelected= it==selected) }
}

inline fun<reified T> toSelectableList(items:List<T>,selected: T?):List<SelectableItem<T>>{
    return items.map { SelectableItem(it,isSelected = it==selected) }
}