package com.vm.eea.ui.calculators.main.activePowerCalculator

import androidx.lifecycle.ViewModel
import com.vm.eea.application.SelectableItem
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ActivePowerCalculatorViewModel() :ContainerHost<UiState,Effect>,ViewModel() {

    override val container: Container<UiState, Effect>
        = container(UiState.init())


    fun onInputChangeRequestSelect()=intent{
        postSideEffect(Effect.ShowModal())
    }

    fun onInputTypeChange(item:SelectableItem<ActivePowerInputType>)=intent {
        reduce { state.copy(currentInput = item.value) }
    }


}