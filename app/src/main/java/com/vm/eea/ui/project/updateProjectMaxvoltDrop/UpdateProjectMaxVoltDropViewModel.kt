package com.vm.eea.ui.project.updateProjectMaxvoltDrop

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.RelationType
import com.vm.eea.domain.VoltDrop
import com.vm.eea.domain.format
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.UpdateProjectMaxVoltDrop
import com.vm.eea.utilities.*
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectMaxVoltDropViewModel(
    private val projectId: Long,
    private val relationType: RelationType,
    private val getProject: GetProject,
    private val updateProjectMaxVoltDrop: UpdateProjectMaxVoltDrop
):ContainerHost<UpdateProjectMaxVoltDropState,Nothing>,ViewModel() {
    override val container: Container<UpdateProjectMaxVoltDropState, Nothing>
         = container(UpdateProjectMaxVoltDropState.init(relationType)){
                     intent {
                         getProject(projectId).collect {
                             val currentValue=when(relationType){
                                 RelationType.PanelToPanel -> it.panelToPanelMaxVoltDrop
                                 RelationType.PanelToMotor -> it.panelToMotorMaxVoltDrop
                             }
                         reduce { state.copy(value = currentValue.value.format(),canExecute = true) }
                     }

             }
    }

    fun onChange(value:String)=intent{
        val validationResult=Validator.validate.inRange(value,1.0,100.0,"")
        reduce { state.copy(value = value,
            error = validationResult?.let { SimpleText(it.message) },
        canExecute = validationResult==null) }
    }

   fun onSubmit()=intent{
        updateProjectMaxVoltDrop(projectId, VoltDrop(state.value.toDouble()),relationType)
    }
}