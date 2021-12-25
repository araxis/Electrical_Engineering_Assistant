package com.vm.eea.data.motor

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vm.eea.application.Power
import com.vm.eea.application.StartMode
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.data.model.FullMotorView
import kotlinx.coroutines.flow.Flow
import androidx.room.Update




@Dao
interface IMotorWriteDao {

    @Query("select * from full_motor_view where projectCode=:projectCode ORDER BY loadId ASC   Limit 1 ")
    suspend fun getFullMotor(projectCode:String):FullMotorView

    @Query("select * from full_motor_view where loadId=:motorId")
    suspend fun getFullMotor(motorId: Long):FullMotorView

    @Query("select voltage_volt from full_motor_view where loadId=:motorId")
    suspend fun getMotorVoltage(motorId: Long):Double

    @Query("SELECT * FROM loads where projectId = :projectId")
    fun all(projectId:Long): Flow<List<LoadEntity>>

    @Update
    suspend fun update(motor: LoadEntity)

    @Query("select * from full_motor_view where projectId= :projectId")
    fun getProjectMotors(projectId:Long):Flow<List<FullMotorView>>

    @Query("select * from full_motor_view where feeder_id= :feederId")
    suspend fun getPanelMotors(feederId:Long):List<FullMotorView>

    @Query("select * from full_motor_view where loadId= :motorId")
    fun getProjectMotor(motorId:Long):Flow<FullMotorView>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: LoadEntity):Long

    @Query("SELECT * FROM loads where (id = :id and loadType = :loadType)")
    suspend fun get(id:Long,loadType: LoadType): LoadEntity

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

    @Query("UPDATE loads SET protectionType = :value  WHERE id = :id")
    suspend fun updateProtectionType(id: Long, value: ProtectionType)

    @Query("UPDATE loads SET isBiDirect = :value  WHERE id = :id")
    suspend fun updateIsBiDirect(id: Long, value: Boolean)

    @Query("UPDATE loads SET hasOverLoadRelay = :value  WHERE id = :id")
    suspend fun updateHasOverLoadRelay(id: Long, value: Boolean)



}