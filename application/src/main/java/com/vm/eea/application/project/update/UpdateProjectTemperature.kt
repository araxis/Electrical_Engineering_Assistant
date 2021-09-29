package com.vm.eea.application.project.update

import com.vm.eea.application.Temperature
import com.vm.eea.application.Environment
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.ProjectId

class UpdateProjectTemperature(private val repository: IProjectRepository) {
     suspend operator fun invoke(projectId: ProjectId, value: Temperature, environment: Environment) {
        when(environment){
            Environment.Ambient -> repository.updateAmbientTemperature(projectId,value)
            Environment.Ground -> repository.updateSoilTemperature(projectId,value)
        }
    }
}