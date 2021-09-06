package com.vm.eea.domain.project

import com.vm.eea.domain.UnitOfWireSize

class UpdateProjectUnitOfWireSize(private val projectRepository: IProjectRepository) {
    suspend operator fun invoke(projectId:Long,unit:UnitOfWireSize){
        projectRepository.updateUnitOfWireSize(projectId,unit)
    }
}