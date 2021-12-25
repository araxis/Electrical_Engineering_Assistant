package com.vm.eea.ui.home

import androidx.lifecycle.ViewModel
import com.vm.eea.application.calculators.applicationProject.motorProject.AddMotorApplicationProject
import com.vm.eea.application.calculators.applicationProject.panelProject.AddPanelApplicationProject
import com.vm.eea.application.project.SimpleProject
import com.vm.eea.ui.MenuItem
import com.vm.eea.ui.NavigationManager
import com.vm.eea.utilities.onIO
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class HomeViewModel(private val getCalculatorList: GetCalculatorList,
                    private val addMotorApplicationProject: AddMotorApplicationProject,
                    private val addPanelApplicationProject: AddPanelApplicationProject,
                    private val navigationManager: NavigationManager):ContainerHost<UiState,Nothing>,ViewModel() {

    init {
        onIO {
            addMotorApplicationProject()
            addPanelApplicationProject()
        }

    }

    override val container: Container<UiState, Nothing>
         = container(UiState()){
              intent {
                  val calculators=getCalculatorList()
                  reduce { state.copy(calculators = calculators) }
              }
    }


    fun onMenuItemClicked(item:MenuItem){
        navigationManager.navigate(item.roue())
    }



}