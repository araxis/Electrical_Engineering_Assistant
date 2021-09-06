package com.vm.eea.ui.project.projectList

import com.vm.eea.domain.project.SimpleProject

//sealed class ProjectListState {
//    object Loading:ProjectListState()
//    data class Loaded(val projects: List<SimpleProject>):ProjectListState()
//
//}



    data class ProjectListState(val projects: List<SimpleProject>,val loading:Boolean){
        companion object{
            fun init()=ProjectListState(emptyList(),true)
        }
    }

