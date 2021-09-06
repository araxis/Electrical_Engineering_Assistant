package com.vm.eea.domain.project

import com.vm.eea.domain.MethodOfInstallation

class UpdateProjectMethodOfInstallation(private val projectRepository: IProjectRepository) {

    suspend operator fun invoke(projectId:Long,value:MethodOfInstallation){
          projectRepository.updateMethodOfInstallation(projectId,value)
    }
}