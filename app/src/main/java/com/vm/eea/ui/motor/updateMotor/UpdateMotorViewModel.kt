package com.vm.eea.ui.motor.updateMotor

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorViewModel:ContainerHost<UiState,Effect>,ViewModel() {
    override val container: Container<UiState, Effect>
         = container(UiState.init()){

    }
}