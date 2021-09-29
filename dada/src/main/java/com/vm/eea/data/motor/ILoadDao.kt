package com.vm.eea.data.motor

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vm.eea.application.Power
import com.vm.eea.application.StartMode
import com.vm.eea.data.model.FullMotorView
import com.vm.eea.data.model.LoadEntity
import com.vm.eea.data.model.LoadType
import kotlinx.coroutines.flow.Flow

@Dao
interface ILoadDao {
//
    @Query("SELECT * FROM loads where projectId = :projectId")
    fun all(projectId:Long): Flow<List<LoadEntity>>

    @Query("select * from full_motor_view where projectId= :projectId")
    fun getProjectMotors(projectId:Long):Flow<List<FullMotorView>>

    @Query("select * from full_motor_view where id= :motorId")
    fun getProjectMotor(motorId:Long):Flow<FullMotorView>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: LoadEntity):Long

    @Query("SELECT * FROM loads where (id = :id and loadType = :loadType)")
    suspend fun get(id:Long,loadType: LoadType):LoadEntity

    @Query("SELECT * FROM loads where (id = :id and loadType = :loadType)")
    fun getLoadFlow(id:Long,loadType: LoadType):Flow<LoadEntity>

//
    @Query("DELETE FROM loads WHERE id = :loadId")
    suspend fun deleteById(loadId: Long)
//
    @Query("UPDATE loads SET powerSupplyPath = :path WHERE id = :loadId")
    suspend fun updatePath(loadId: Long,path:String)

    @Query("UPDATE loads SET powerSupplyPath = `replace`(powerSupplyPath, :oldStartPath, :newStartPath ) WHERE (projectId = :projectId and powerSupplyPath LIKE :oldStartPath || '%')")
    fun replaceStartPaths(projectId:Long, oldStartPath: String, newStartPath:String)

    @Query("UPDATE loads SET code = :code,description= :description WHERE id = :loadId")
    suspend fun updateCode(loadId: Long, code: String, description: String)

    @Query("UPDATE loads SET power_value = :value , power_unit= :unit WHERE id = :id")
    fun updatePower(id: Long, value: Double, unit: Power.Unit)

    @Query("UPDATE loads SET cosPhi = :cosPhi ,sinPhi= :sinPhi,tanPhi=:tanPhi WHERE id = :id")
    suspend fun updatePowerfactor(id: Long, cosPhi: Double,sinPhi:Double,tanPhi:Double)

    @Query("UPDATE loads SET demandFactorCosPhi = :cosPhi,demandFactorTanPhi=:tanPhi  WHERE id = :id")
    suspend fun updateDemandFactor(id: Long, cosPhi: Double,tanPhi: Double)

    @Query("UPDATE loads SET efficiency_value = :value  WHERE id = :id")
    suspend fun updateEfficiency(id: Long, value: Double)

    @Query("UPDATE loads SET startMode = :value  WHERE id = :id")
    suspend fun updateStartMode(id: Long, value: StartMode)

}