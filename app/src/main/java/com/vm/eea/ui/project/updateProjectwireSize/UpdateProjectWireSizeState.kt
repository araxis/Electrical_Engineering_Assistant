package com.vm.eea.ui.project.updateProjectwireSize

import com.vm.eea.domain.WireSize
import com.vm.eea.domain.project.WireSizeType
import com.vm.eea.ui.SelectableItem

data class UpdateProjectWireSizeState(val pageTitle:String,
                                      val defaults:List<SelectableItem<WireSize>>) {

    companion object{
        fun init(wireSizeType: WireSizeType) = when(wireSizeType){
              WireSizeType.Max -> UpdateProjectWireSizeState("Max Wire size", emptyList())
              WireSizeType.Min -> UpdateProjectWireSizeState("Min Wire size", emptyList())
          }
    }
}

data class DefaultWireSizeItem(val value: WireSize, val isCustom:Boolean, val isSelected:Boolean)