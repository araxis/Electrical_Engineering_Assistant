package com.vm.eea.ui.project.updateProjectMaxvoltDrop

import androidx.lifecycle.ViewModel
import com.vm.eea.application.VoltDrop
import com.vm.eea.application.format
import com.vm.eea.application.project.IGetProjectMaxVoltDrop
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.RelationType
import com.vm.eea.application.project.update.UpdateProjectMaxVoltDrop
import com.vm.eea.utilities.SimpleText
import com.vm.eea.utilities.Validator
import com.vm.eea.utilities.inRange
import com.vm.eea.utilities.onIO
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class UpdateProjectMaxVoltDropViewModel(
    private val projectId: ProjectId,
    private val relationType: RelationType,
    private val getProject: IGetProjectMaxVoltDrop,
    private val updater: UpdateProjectMaxVoltDrop
):ContainerHost<UiState,Nothing>,ViewModel() {
    override val container: Container<UiState, Nothing>
         = container(UiState.init(relationType)){
                    onIO {
                        val voltDrop=getProject(projectId,relationType)
                        intent {

                            reduce { state.copy(value = voltDrop.value.format(),canExecute = true) }
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
       updater(projectId, VoltDrop(state.value.toDouble()),relationType)
    }
}