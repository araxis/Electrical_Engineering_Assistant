package com.vm.eea.domain.project

import com.vm.eea.domain.WireSize

class UpdateProjectWireSize(private val projectRepository:IProjectRepository) {
    suspend operator fun invoke(projectId:Long, size: WireSize, type: WireSizeType){
        when(type){
            WireSizeType.Max -> projectRepository.updateMaxWireSize(projectId,size)
            WireSizeType.Min -> projectRepository.updateMinWireSize(projectId,size)
        }
    }
}

