package com.vm.eea.ui.project.projectCenter

import androidx.lifecycle.ViewModel
import com.vm.eea.application.project.IGetProjectMotors
import com.vm.eea.application.project.IGetProjectPanels
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.ProjectMotorsResult
import com.vm.eea.data.project.GetProjectMotors
import com.vm.eea.data.project.GetProjectPanels
import com.vm.eea.ui.Destinations
import com.vm.eea.ui.MotorDestinations
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.PanelDestinations
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProjectCenterViewModel(
    private val projectId: ProjectId,
    private val getProjectMotors: IGetProjectMotors,
    private val getProjectPanels: IGetProjectPanels,
    private val navigationManager: NavigationManager
):ContainerHost<UiState,Nothing>,ViewModel() {


    override val container: Container<UiState, Nothing> = container(UiState.init()){
        intent {
            getProjectPanels(projectId).combine(getProjectMotors.getProjectMotors(projectId)){ panels, motors->
                ProjectParts(panels,motors)
            }.collect {
                reduce { state.copy(panels = it.panels,motors = it.motors) }
            }

        }
    }



    fun onTabSelect(tab: UiState.ProjectCenterTab)=intent{
        reduce { state.copy(currentTab = tab) }
    }

    fun onPanelSelect(panel: IGetProjectPanels.Result){
        navigationManager.navigate(PanelDestinations.PanelDetails(panel.id))
    }

    fun onMotorSelect(motor: ProjectMotorsResult){
        navigationManager.navigate(MotorDestinations.MotorDetails(motor.id))
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

   private data class ProjectParts(val panels:List<IGetProjectPanels.Result>, val motors:List<IGetProjectMotors.Result>)



}