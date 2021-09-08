package com.vm.eea.ui.project.updateProjectwireSize

import com.vm.eea.domain.WireSize
import com.vm.eea.domain.project.WireSizeType
import com.vm.eea.ui.SelectableItem

data class UiState(val pageTitle:String,
                   val defaults:List<SelectableItem<WireSize>>) {

    companion object{
        fun init(wireSizeType: WireSizeType) = when(wireSizeType){
              WireSizeType.Max -> UiState("Max Wire size", emptyList())
              WireSizeType.Min -> UiState("Min Wire size", emptyList())
          }
    }
}

