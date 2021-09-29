package com.vm.eea.application.project

import com.vm.eea.application.Environment
import com.vm.eea.application.Temperature


interface IGetProjectTemperature {

    suspend operator fun invoke(projectId: ProjectId, environment: Environment): Temperature


}