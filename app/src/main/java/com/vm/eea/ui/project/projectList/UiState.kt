package com.vm.eea.ui.project.projectList

import com.vm.eea.application.project.IGetProjectSimpleList


data class UiState(val projects: List<IGetProjectSimpleList.SimpleProject>, val loading:Boolean){
        companion object{
            fun init()=UiState(emptyList(),true)
        }
    }

