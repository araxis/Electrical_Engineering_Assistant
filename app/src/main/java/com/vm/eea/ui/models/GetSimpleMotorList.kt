package com.vm.eea.ui.models

import com.vm.eea.domain.load.IMotorRepository
import com.vm.eea.domain.project.ProjectId
import kotlinx.coroutines.flow.map

class GetSimpleMotorList(private val repository: IMotorRepository) {

    operator fun invoke(projectId: ProjectId)=repository.get(projectId.id)
        .map { items->items.map { SimpleMotor(ProjectId(it.projectId),it.id,it.code,it.description) } }
}