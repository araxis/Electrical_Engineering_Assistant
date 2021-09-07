package com.vm.eea.domain.project

import com.vm.eea.domain.UnitOfTemperature

class UpdateProjectUnitOfTemperature(private val projectRepository: IProjectRepository) {
    suspend operator fun invoke(projectId:Long,unit: UnitOfTemperature){
        projectRepository.updateUnitOfTemperature(projectId,unit)
    }
}