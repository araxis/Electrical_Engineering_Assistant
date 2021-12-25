package com.vm.eea.data.project

import com.vm.eea.application.*
import com.vm.eea.application.calculators.applicationProject.motorProject.IApplicationProjectCodeResolver
import com.vm.eea.application.project.*
import com.vm.eea.data.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectRepository(
    private val db: AppDatabase,
    private val applicationProjectCodeResolver: IApplicationProjectCodeResolver,
) : IProjectRepository {

   override suspend fun isExist(code: String): Boolean{
        return db.projectDao().isExist(code)
    }

    override suspend fun add(newProject: Project): ProjectId {
        val entity = toEntity(newProject)

        val id=  db.projectDao().insertProject(entity)
        return ProjectId(id)
    }

    override fun getProjects(): Flow<List<SimpleProject>> {
        val exceptions= listOf(applicationProjectCodeResolver.motorProjectCode(),
            applicationProjectCodeResolver.panelProjectCode())
        return db.projectDao().getSimpleProjects(exceptions)
            .map { list->list.map { SimpleProject(ProjectId(it.id),
                it.code,it.description,Current(it.totalCurrent,Current.Unit.A)) } }
    }

    override suspend fun get(projectId: ProjectId): Project {
        val entity=db.projectDao().get(projectId.id)
        return with(entity){

            Project( projectId,code=code,
            description=description,
            lineToNullVoltage=lineToNullVoltage,
            lineToLineVoltage=lineToLineVoltage,
            altitude=altitude,
            methodOfInstallation=methodOfInstallation,
            ambientTemperature=ambientTemperature,
            groundTemperature=groundTemperature,
            soilResistivity=soilResistivity,
            conductor=conductor,
            insulation=insulation,
             panelToPanelMaxVoltDrop=panelToPanelMaxVoltDrop,
            panelToMotorMaxVoltDrop=panelToMotorMaxVoltDrop,
            circuitInTheSameConduit=circuitInTheSameConduit,
            maxWireSize=maxWireSize,
            minWireSize=minWireSize,
            standard=standard,
            )
        }
    }

    override suspend fun remove(projectId: ProjectId) {
        db.projectDao().deleteById(projectId.id)
    }

    override suspend fun update(project: Project) {
        val entity=toEntity(project)
        db.projectDao().update(entity)
    }

    private fun toEntity(newProject: Project): ProjectEntity {
        return ProjectEntity(
            id=newProject.projectId.id,
            code = newProject.code,
            description = newProject.description,
            altitude = newProject.altitude,
            ambientTemperature = newProject.ambientTemperature,
            circuitInTheSameConduit = newProject.circuitInTheSameConduit,
            conductor = newProject.conductor,
            groundTemperature = newProject.groundTemperature,
            insulation = newProject.insulation,
            maxWireSize = newProject.maxWireSize,
            methodOfInstallation = newProject.methodOfInstallation,
            minWireSize = newProject.minWireSize,
            isDeleted = false,
            lineToNullVoltage = newProject.lineToNullVoltage,
            panelToMotorMaxVoltDrop = newProject.panelToMotorMaxVoltDrop,
            panelToPanelMaxVoltDrop = newProject.panelToPanelMaxVoltDrop,
            soilResistivity = newProject.soilResistivity,
            standard = newProject.standard,
            lineToLineVoltage = newProject.lineToLineVoltage
        )
    }




    override suspend fun updateLineToNullVoltage(projectId: ProjectId, value: Double) {
        db.projectDao().updateLineToNullVoltage(projectId.id,value)
    }



    override suspend fun updateLineToLineVoltage(projectId: ProjectId, value: Double) {
        db.projectDao().updateLineToLineVoltage(projectId.id,value)
    }

}