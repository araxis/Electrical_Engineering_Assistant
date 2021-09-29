package com.vm.eea.application.motor.addMotor

import com.vm.eea.application.PanelId


interface IGetPanelToMotorDefaultRelationConfig {
    suspend operator fun invoke(panelId: PanelId): PanelToMotorRelationConfig
}