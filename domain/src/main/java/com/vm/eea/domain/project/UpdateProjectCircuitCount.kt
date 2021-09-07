package com.vm.eea.domain.project

import com.vm.eea.domain.CircuitCount

class UpdateProjectCircuitCount(private val projectRepository: IProjectRepository) {

    suspend operator fun invoke(projectId:Long,value: CircuitCount){
        projectRepository.updateCircuitInTheSameConduit(projectId,value)
    }
}