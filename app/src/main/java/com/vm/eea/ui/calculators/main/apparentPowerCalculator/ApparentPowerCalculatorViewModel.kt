package com.vm.eea.ui.calculators.main.apparentPowerCalculator

import androidx.lifecycle.ViewModel
import com.vm.eea.application.SelectableItem
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ApparentPowerCalculatorViewModel():
    ContainerHost<State, Effect>, ViewModel() {


    override val container: Container<State, Effect>
        = container(State.init())

    fun onInputTypeChange(selectableItem: SelectableItem<ApparentPowerInputType>)=intent {
        reduce { state.copy(currentInput = selectableItem.value)}
    }

    fun onInputChangeRequestSelect()=intent {
        postSideEffect(Effect.ShowModal())

    }

}