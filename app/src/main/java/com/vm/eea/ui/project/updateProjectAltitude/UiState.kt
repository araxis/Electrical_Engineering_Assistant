package com.vm.eea.ui.project.updateProjectAltitude

import com.vm.eea.application.Length
import com.vm.eea.utilities.IText

data class UiState(val value:String,
                   val unit: Length.Unit,
                   val error: IText?,
                   val canExecute:Boolean){
    companion object{
        fun init() =UiState("",
            Length.Unit.M,
            null,
            canExecute = true)
    }
}


