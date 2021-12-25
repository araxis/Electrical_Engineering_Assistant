package com.vm.eea.ui.panel.fullPanelUpdater

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.components.CoincidenceFactorInput
import com.vm.eea.ui.components.CosPhiInput
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.StringInput

@Composable
fun FullPanelUpdateScreen(viewModel: FullPanelUpdateViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Panel Edit",canSubmit = state.canCalculate,onSubmit = {viewModel.update()}) {
        Column {
            StringInput(label = "Code",field = state.code,onChange = { viewModel.onCodeChange(it) })
            CosPhiInput(label = "Demand factor", field = state.demandFactor, onChange ={viewModel.onDemandFactorChange(it)} )
            CoincidenceFactorInput(field = state.coincidenceFactor,onChange = {viewModel.onCoincidenceFactorChange(it)})
        }

    }
}