package com.vm.eea.data.project

import com.vm.eea.application.Current
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.project.IGetProjectMotors
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.ProjectMotorsResult
import com.vm.eea.data.AppDatabase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProjectMotors(private val db: AppDatabase) : IGetProjectMotors {

    override fun getProjectMotors(projectId: ProjectId):Flow<List<ProjectMotorsResult>>{

       return db.projectReadDao().getSimpleProjectMotors(projectId.id)
           .map { list->list.map {
               ProjectMotorsResult(
                   MotorId(it.id), it.code, it.description,
                   Current(it.current, Current.Unit.A)
               )
           } }

    }


}