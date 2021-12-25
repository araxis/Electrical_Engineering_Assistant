package com.vm.eea.application.calculators.applicationProject.panelProject

import com.vm.eea.application.PanelId
import kotlinx.coroutines.flow.Flow

interface IGetPanelMotors {
    suspend operator fun invoke(panelId: PanelId):Flow<List<PanelMotor>>
}