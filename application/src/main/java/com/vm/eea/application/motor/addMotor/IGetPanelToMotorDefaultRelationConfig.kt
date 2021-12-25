package com.vm.eea.application.motor.addMotor

import com.vm.eea.application.project.ProjectId


interface IGetPanelToMotorDefaultRelationConfig {
    suspend operator fun invoke(projectId: ProjectId): PanelToMotorRelationConfig
}