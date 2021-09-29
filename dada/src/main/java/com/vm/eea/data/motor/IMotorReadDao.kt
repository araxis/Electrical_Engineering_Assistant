package com.vm.eea.data.motor

import androidx.room.Dao
import androidx.room.Query
import com.vm.eea.application.CosPhi
import com.vm.eea.application.Power
import com.vm.eea.application.StartMode
import com.vm.eea.application.motor.MotorId

@Dao
interface IMotorReadDao {

    @Query("select demandFactorCosPhi from loads where id=:motorId")
    suspend fun getDemandFactor(motorId:Long):Double

    @Query("select efficiency_value from loads where id=:motorId")
    suspend fun getEfficiency(motorId:Long):Double

    @Query("select power_value as value,power_unit as unit from loads where id=:motorId")
    suspend fun getPower(motorId:Long):Power

    @Query("select cosPhi as value from loads where id=:motorId")
    suspend fun getCosPhi(motorId:Long):CosPhi

    @Query("select startMode from loads where id=:motorId")
    fun getStartMode(motorId: Long): StartMode

    @Query("select id,code,description from loads where id=:motorId")
    fun getSimpleInfo(motorId: Long): SimpleMotorDto

    data class SimpleMotorDto(val id: Long, val code:String, val description:String)
}