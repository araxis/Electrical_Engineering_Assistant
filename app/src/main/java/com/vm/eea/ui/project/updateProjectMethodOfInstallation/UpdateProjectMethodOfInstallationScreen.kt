package com.vm.eea.ui.project.updateProjectMethodOfInstallation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.GridSelector
import com.vm.eea.ui.components.Page1

@Composable
fun UpdateProjectMethodOfInstallationScreen(viewModel: UpdateProjectMethodOfInstallationViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Method of installation") {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            GridSelector(state.defaults,modifier= Modifier.padding(top=8.dp),){viewModel.onItemSelect(it)}
    }
    }
}

