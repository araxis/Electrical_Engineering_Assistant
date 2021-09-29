package com.vm.eea.application.project

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.SelectableItem



interface IGetProjectCircuitCount {

    suspend operator fun invoke(projectId: ProjectId):List<SelectableItem<CircuitCount>>


}