package com.vm.eea.ui.project.projectDetailsView

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.components.PropertyList


@Composable
fun ProjectDetailsScreen(viewModel: ProjectDetailViewModel){
    val state by viewModel.container.stateFlow.collectAsState()


      //  ProjectPropertyList(state.navigationItems){viewModel.onItemSelect(it)}
        Page1(pageTitle = "Project Settings") {
            PropertyList(items = state.navigationItems, onItemClick = {viewModel.onItemSelect(it)})
        }



}



