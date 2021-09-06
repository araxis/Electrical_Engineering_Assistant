package com.vm.eea.ui.panel.updatePanelFeedLength

import com.vm.eea.domain.UnitOfLength
import com.vm.eea.utilities.IText

data class UiState(val value:String,val unit:UnitOfLength, val canExecute:Boolean, val error: IText? ) {

    companion object{
        fun init()=
            UiState("", UnitOfLength.M, false, null)
    }
}