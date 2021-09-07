package com.vm.eea.data

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vm.eea.data.model.PanelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IPanelDao {

    @Query("SELECT * FROM panels where projectId = :projectId")
    fun all(projectId:Long): Flow<List<PanelEntity>>

    @Query("SELECT * FROM panels where id = :panelId")
    suspend fun getPanel(panelId: Long): PanelEntity

    @Query("SELECT * FROM panels where id = :panelId")
    fun getPanelFlow(panelId: Long): Flow<PanelEntity>

    @Query("SELECT * FROM panels where (powerSupplyPath = :supplyPath and projectId= :projectId)")
    fun getPanelFlow(projectId: Long,supplyPath: String): Flow<PanelEntity>



    @Query("UPDATE panels SET code = :code,description=:description WHERE id = :panelId")
    suspend fun updateCode(panelId: Long,code:String,description:String)


//    @Query("SELECT MAX(id) as id,code,description,isMdp,powerSupplyPath,projectId FROM panels where projectId= :projectId")
//    suspend fun getLastPanel(projectId: Long): PanelEntity

    @Query("SELECT * FROM panels where ( projectId = :projectId and isMdp =1)")
    suspend fun getMdp(projectId: Long): PanelEntity

    @Query("Select * from panels where(projectId=:projectId and powerSupplyPath not LIKE :startPath || '%')")
    fun getPanelsNotSupplyWith(projectId:Long,startPath:String):Flow<List<PanelEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: PanelEntity):Long

    @Query("DELETE FROM panels WHERE id = :panelId")
    suspend fun deleteById(panelId: Long)

    @Query("UPDATE panels SET powerSupplyPath = :path WHERE id = :panelId")
    fun updatePath(panelId: Long,path:String): Int

    @Query("UPDATE panels SET powerSupplyPath = `replace`(powerSupplyPath, :oldStartPath, :newStartPath ) WHERE (projectId = :projectId and powerSupplyPath LIKE :oldStartPath || '%')")
    suspend fun updateSupplyPaths(projectId:Long,oldStartPath: String,newStartPath:String)



}