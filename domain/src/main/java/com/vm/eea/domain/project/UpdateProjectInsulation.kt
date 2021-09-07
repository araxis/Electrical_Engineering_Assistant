package com.vm.eea.domain.project

import com.vm.eea.domain.Insulation

class UpdateProjectInsulation(private val projectRepository: IProjectRepository) {

    suspend operator fun invoke(projectId:Long,value: Insulation){
          projectRepository.updateInsulation(projectId,value)
    }
}