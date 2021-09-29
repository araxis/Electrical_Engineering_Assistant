package com.vm.eea.ui.project.projectList

import androidx.lifecycle.ViewModel
import com.vm.eea.application.project.IGetProjectSimpleList
import com.vm.eea.ui.Destinations
import com.vm.eea.ui.NavigationManager
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProjectListViewModel(
    getSimpleProjects:IGetProjectSimpleList,
    private val navigationManager: NavigationManager,
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing> =container(UiState.init()){
               intent {
                getSimpleProjects().collect {
                    reduce { state.copy(projects = it,loading = false) }
                }
        }
    }

    fun showAddView()=intent{

      navigationManager.navigate(Destinations.NewProject())

    }

    fun onItemSelect(item: IGetProjectSimpleList.SimpleProject){
        navigationManager.navigate(Destinations.ProjectCenter(item.id))
    }
}