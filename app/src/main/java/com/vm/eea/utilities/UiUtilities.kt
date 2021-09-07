package com.vm.eea.utilities

import com.vm.eea.ui.SelectableItem

inline fun<reified T:Enum<T>> toSelectableList(selected:T?=null):List<SelectableItem<T>>{

    return  enumValues<T>().map { SelectableItem(it,isSelected= it==selected) }
}