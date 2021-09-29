package com.vm.eea.ui.panel.updatePanelFeedLength


import com.vm.eea.application.Length
import com.vm.eea.utilities.IText

data class UiState(val value:String, val unit: Length.Unit, val canExecute:Boolean, val error: IText? ) {

    companion object{
        fun init()=
            UiState("", Length.Unit.M, false, null)
    }
}