package com.vm.eea.data.project

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface IProjectDao {

    @Query("select exists( select 1 from projects where (code=:code))")
    suspend fun isExist(code: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProject(projectEntity: ProjectEntity):Long

    @Query("select full_motor_view.powerSupplyPath from full_motor_view where full_motor_view.feeder_id=:panelId\n" +
            "union\n" +
            "select full_panel_view.powerSupplyPath from full_panel_view where full_panel_view.feederId=:panelId")
    suspend fun getPanelChildPaths(panelId:Long):List<String>

    @Query("select id,code,description,totalCurrent from full_project_view where code NOT IN (:exceptionCodes)")
    fun getSimpleProjects(exceptionCodes: List<String>): Flow<List<SimpleProjectDto>>

    @Query("select * from projects where id=:projectId")
    suspend fun get(projectId: Long): ProjectEntity

    @Update
    suspend fun update(projectEntity: ProjectEntity)

    @Delete
    fun delete(model: ProjectEntity)

    @Query("DELETE FROM projects WHERE code = :projectCode")
    suspend fun deleteByCode(projectCode:String)

    @Query("DELETE FROM projects WHERE id = :projectId")
    suspend fun deleteById(projectId: Long)


    @Query("UPDATE projects SET lineToNullVoltage = :value WHERE id = :projectId")
    suspend fun updateLineToNullVoltage(projectId:Long, value: Double)

    @Query("UPDATE projects SET lineToLineVoltage = :value WHERE id = :projectId")
    suspend fun updateLineToLineVoltage(projectId:Long, value: Double)


    data class SimpleProjectDto(val id: Long, val code:String, val description:String,val totalCurrent:Double)

}