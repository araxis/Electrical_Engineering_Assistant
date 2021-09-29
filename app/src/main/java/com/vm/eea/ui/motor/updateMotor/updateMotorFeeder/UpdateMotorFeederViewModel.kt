package com.vm.eea.ui.motor.updateMotor.updateMotorFeeder

import androidx.lifecycle.ViewModel
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.motor.IGetMotorFeeder
import com.vm.eea.application.motor.MotorFeeder
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.motor.updateMotor.UpdateMotorFeeder
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorFeederViewModel(private val motorId:MotorId,
                                 private val updater: UpdateMotorFeeder,
                                 private val getMotorFeeder: IGetMotorFeeder,
                                 private val navigationManager: NavigationManager):ContainerHost<UiState,Nothing>,ViewModel() {


    override val container: Container<UiState, Nothing>
         = container(UiState()){
             onIO {
                 val items=getMotorFeeder(motorId)
                 intent {
                     reduce { state.copy(feeder = items) }
                 }
             }
    }

    fun onSelect(it: SelectableItem<MotorFeeder>) =onIO{
        updater(motorId,it.value.id)
        navigationManager.back()
    }
}