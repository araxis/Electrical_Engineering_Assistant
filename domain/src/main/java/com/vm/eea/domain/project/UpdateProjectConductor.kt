package com.vm.eea.domain.project

import com.vm.eea.domain.Conductor

class UpdateProjectConductor(private val projectRepository: IProjectRepository) {

    suspend operator fun invoke(projectId:Long,value: Conductor){
          projectRepository.updateConductor(projectId,value)
    }
}