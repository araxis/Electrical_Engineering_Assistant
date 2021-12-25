package com.vm.eea.application.project

import kotlinx.coroutines.flow.Flow


interface IProjectRepository {
    suspend fun add(newProject: Project): ProjectId
    fun getProjects(): Flow<List<SimpleProject>>
    suspend fun get(projectId: ProjectId):Project
    suspend fun remove(projectId: ProjectId)
    suspend fun update(project: Project)
    suspend fun updateLineToNullVoltage(projectId: ProjectId, value: Double)
    suspend fun updateLineToLineVoltage(projectId: ProjectId, value: Double)

    suspend fun isExist(code: String): Boolean
}

