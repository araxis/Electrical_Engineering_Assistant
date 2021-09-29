package com.vm.eea.data.project

import com.vm.eea.application.PowerSystem
import com.vm.eea.application.Voltage
import com.vm.eea.application.project.IGetProjectVoltage
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetProjectVoltage(private val db:AppDatabase):IGetProjectVoltage {
    override suspend fun invoke(projectId: ProjectId, system: PowerSystem): Voltage {
        return when(system){
            PowerSystem.SinglePhase -> db.projectReadDao().getSinglePhaseVoltage(projectId.id)
            PowerSystem.TwoPhase -> db.projectReadDao().getTwoPhaseVoltage(projectId.id)
            PowerSystem.ThreePhase -> db.projectReadDao().getThreePhaseVoltage(projectId.id)
        }.let { Voltage(it,Voltage.Unit.V) }
    }
}