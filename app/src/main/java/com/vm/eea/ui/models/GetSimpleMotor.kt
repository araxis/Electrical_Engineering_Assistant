package com.vm.eea.ui.models

import com.vm.eea.domain.load.IMotorRepository
import com.vm.eea.domain.load.LoadId
import com.vm.eea.domain.project.ProjectId
import kotlinx.coroutines.flow.map

class GetSimpleMotor(private val repository: IMotorRepository) {

    operator fun invoke(motorId:LoadId)=repository
        .getMotorFlow(motorId)
        .map { SimpleMotor(ProjectId(it.projectId),it.id,it.code,it.description) }
}