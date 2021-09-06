package com.vm.eea.domain.project

import com.vm.eea.domain.UnitOfPower

class UpdateProjectUnitOfPower(private val projectRepository: IProjectRepository) {

    suspend operator fun invoke(projectId:Long,value:UnitOfPower){
          projectRepository.updateUnitOfPower(projectId,value)
    }
}