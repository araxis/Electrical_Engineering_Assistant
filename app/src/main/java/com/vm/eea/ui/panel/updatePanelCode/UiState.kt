package com.vm.eea.ui.panel.updatePanelCode

import com.vm.eea.ui.project.updateProjectCode.UiState
import com.vm.eea.utilities.IText

data class UiState (val code:String,val description:String,val codeError:IText?,val canSubmit:Boolean){
    companion object{
        fun init()= UiState("","",null,false)
    }
}