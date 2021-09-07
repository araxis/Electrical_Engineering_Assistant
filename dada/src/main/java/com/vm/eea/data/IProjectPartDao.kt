package com.vm.eea.data

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vm.eea.data.model.PanelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IProjectPartDao {

    @Query("""
        select powerSupplyPath from panels
          where ((LENGTH(powerSupplyPath)- LENGTH( REPLACE ( powerSupplyPath,"/", "") ))= :pathLength and projectId= :projectId)
           UNION
           select powerSupplyPath from loads
          where ((LENGTH(powerSupplyPath)- LENGTH( REPLACE ( powerSupplyPath,"/", "") ))= :pathLength and projectId= :projectId)
    """)
    suspend fun getPathWithLength(projectId:Long,pathLength:Int):List<String>

    @Query("""
        select powerSupplyPath from panels
          where ((LENGTH(powerSupplyPath)- LENGTH( REPLACE ( powerSupplyPath,"/", "") ))= :pathLength
                 and  powerSupplyPath LIKE :startPath || '%' and projectId= :projectId)
           UNION
           select powerSupplyPath from loads
          where ((LENGTH(powerSupplyPath)- LENGTH( REPLACE ( powerSupplyPath,"/", "") ))= :pathLength 
                    and powerSupplyPath LIKE :startPath || '%' and projectId= :projectId)
    """)
    suspend fun getPathWithLength(projectId: Long,startPath:String,pathLength:Int):List<String>

    @Query("SELECT * FROM panels where projectId = :projectId")
    fun all(projectId:Long): Flow<List<PanelEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: PanelEntity):Long

    @Query("DELETE FROM panels WHERE id = :panelId")
    suspend fun deleteById(panelId: Long)

    @Query("UPDATE panels SET powerSupplyPath = :path WHERE id = :panelId")
    fun updatePath(panelId: Long,path:String): Int
}