package com.vm.eea.application.project.update

import com.vm.eea.application.WireSize
import com.vm.eea.application.WireSizeType
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.ProjectId

class UpdateProjectWireSize(private val repository: IProjectRepository) {

    suspend operator fun invoke(projectId: ProjectId, value:WireSize, type:WireSizeType){
        when(type){
            WireSizeType.Max -> repository.updateMaxWireSize(projectId,value)
            WireSizeType.Min -> repository.updateMinWireSize(projectId,value)
        }
    }
}