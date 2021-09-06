package com.vm.eea.ui.motor.updateMotor.updateMotorFeeder

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.load.UpdateMotorFeeder
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.SelectableItem
import com.vm.eea.ui.models.SimplePanel
import com.vm.eea.utilities.onIO
import com.vm.eea.utilities.toMotorId
import com.vm.eea.utilities.toPanelId
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateMotorFeederViewModel(private val motorId:Long,
                                 private val updateMotorFeeder: UpdateMotorFeeder,
                                 private val getMotorFeederDetails: GetMotorFeederDetails,
                                 private val navigationManager: NavigationManager):ContainerHost<UiState,Nothing>,ViewModel() {


    override val container: Container<UiState, Nothing>
         = container(UiState()){
             intent {
                getMotorFeederDetails(motorId.toMotorId()).collect {
                    reduce { state.copy(feeder = it.availableFeeders.map { SelectableItem(it,false) }) }
                }
             }
    }

    fun onSelect(it: SimplePanel) =onIO{
        updateMotorFeeder(motorId.toMotorId(),it.id.toPanelId())
        navigationManager.back()
    }
}