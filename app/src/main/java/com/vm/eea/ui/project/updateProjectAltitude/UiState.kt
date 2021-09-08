package com.vm.eea.ui.project.updateProjectAltitude

import com.vm.eea.domain.Length
import com.vm.eea.domain.UnitOfLength
import com.vm.eea.ui.SelectableItem
import com.vm.eea.utilities.IText

data class UiState(val value:String,
                   val unit: UnitOfLength,
                   val error: IText?,
                   val canExecute:Boolean,
                   val addToDefaults:Boolean,
                   val defaults:List<SelectableItem<Length>>){
    companion object{
        fun init() =UiState("",
            UnitOfLength.M,
            null,
            canExecute = false,
            addToDefaults = true,
            defaults = emptyList())
    }
}


