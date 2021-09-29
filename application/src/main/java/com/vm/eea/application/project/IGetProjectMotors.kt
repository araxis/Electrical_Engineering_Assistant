package com.vm.eea.application.project


import com.vm.eea.application.Current
import com.vm.eea.application.motor.MotorId
import kotlinx.coroutines.flow.Flow

typealias ProjectMotorsResult=IGetProjectMotors.Result
interface IGetProjectMotors {
    fun getProjectMotors(projectId: ProjectId): Flow<List<ProjectMotorsResult>>

    data class Result(
        val id: MotorId,
        val code: String,
        val description: String,
        val current: Current
    )

}