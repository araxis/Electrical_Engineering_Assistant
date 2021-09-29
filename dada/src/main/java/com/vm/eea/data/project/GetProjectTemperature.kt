package com.vm.eea.data.project

import com.vm.eea.application.Temperature
import com.vm.eea.application.Environment
import com.vm.eea.application.project.IGetProjectTemperature
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class GetProjectTemperature(private val db:AppDatabase):IGetProjectTemperature {
    override suspend fun invoke(projectId: ProjectId, environment: Environment): Temperature {
        return when(environment){
            Environment.Ambient ->  db.projectReadDao().getProjectAmbientTemperature(projectId.id)
            Environment.Ground -> db.projectReadDao().getProjectSoilTemperature(projectId.id)
        }.let { Temperature(it.value,it.unit) }
    }
}