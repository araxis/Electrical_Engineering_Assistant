package com.vm.eea.ui.motor.updateMotor.updateMotorStartMode

import androidx.lifecycle.ViewModel
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.StartMode
import com.vm.eea.application.motor.IGetMotorStartMode
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.motor.updateMotor.UpdateMotorStartMode
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateStartModeViewModel(
    private val motorId: MotorId,
    private val updateMotor: UpdateMotorStartMode,
    private val getMotorStartMode: IGetMotorStartMode,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {

    override val container: Container<UiState, Nothing>
        = container(UiState()){

             onIO {
                 val items=getMotorStartMode(motorId)
                 intent {  reduce { state.copy(items = items) } }
             }


    }

    fun onValueChange(value: SelectableItem<StartMode>)=onIO {
        updateMotor(motorId,value.value)
        navigationManager.back()
    }


}