package com.vm.eea.ui.project.projectCenter

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.panel.GetPanels
import com.vm.eea.domain.panel.Panel
import com.vm.eea.domain.project.ProjectId
import com.vm.eea.ui.Destinations
import com.vm.eea.ui.MotorDestinations
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.PanelDestinations
import com.vm.eea.ui.models.GetSimpleMotorList
import com.vm.eea.ui.models.SimpleMotor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProjectCenterViewModel(
    private val projectId: Long,
    private val getPanels: GetPanels,
    private val getMotors: GetSimpleMotorList,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing> = container(UiState.init()){
        intent {
            getPanels(projectId).combine(getMotors(ProjectId(projectId))){ panels, motors->
               ProjectParts(panels,motors)
            }.collect {
                reduce { state.copy(panels =it. panels,motors =it. motors) }
            }
        }
    }



    fun onTabSelect(tab:ProjectCenterTab)=intent{
        reduce { state.copy(currentTab = tab) }
    }

    fun onPanelSelect(panel:Panel){
        navigationManager.navigate(PanelDestinations.PanelDetails(panel.id))
    }

    fun onMotorSelect(motor: SimpleMotor){
        navigationManager.navigate(MotorDestinations.MotorDetails(motor.loadId.id))
    }

    fun onAddNewPanel(){
        navigationManager.navigate(PanelDestinations.AddPanel(projectId))
    }

    fun onAddNewMotor(){
        navigationManager.navigate(MotorDestinations.AddMotor(projectId))
    }
    fun onSetting(){
        navigationManager.navigate(Destinations.ProjectDetails(projectId))
    }

   private data class ProjectParts(val panels:List<Panel>, val motors:List<SimpleMotor>)



}