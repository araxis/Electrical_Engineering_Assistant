package com.vm.eea.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vm.eea.data.model.*
import com.vm.eea.domain.*

@Database(entities = [ProjectEntity::class,
    PanelEntity::class,
    LoadEntity::class,
    PanelToPanelRelationEntity::class,
    PanelToMotorRelationEntity::class,
    DefaultsEntity::class,
    DefaultVoltageEntity::class,
    DefaultAltitudeEntity::class,
    DefaultTemperatureEntity::class,
    DefaultSoilResistivityEntity::class,
    DefaultPowerFactorEntity::class,
    DefaultWireSizeEntity::class,
    WireSizeMapEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): IProjectDao
    abstract fun panelDao(): IPanelDao
    abstract fun defaultsDao(): IDefaultDao
    abstract fun defaultVoltageDao(): IDefaultVoltageDao
    abstract fun defaultAltitudeDao(): IDefaultAltitudeDao
    abstract fun defaultGroundTemperatureDao(): IDefaultTemperatureDao
    abstract fun defaultSoilResistivityDao(): IDefaultSoilResistivityDao
    abstract fun defaultPowerfactorDao(): IDefaultPowerfactorDao
    abstract fun defaultWireSizeDao(): IDefaultWireSizeDao
    abstract fun wireSizeMapDao(): IWireSizeMapDao
    abstract fun projectPartDao(): IProjectPartDao
    abstract fun loadDao(): ILoadDao
    abstract fun panelToPanelRelationDao(): IPanelToPanelRelationDao
    abstract fun panelToMotorRelationDao(): IPanelToMotorRelationDao
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app-db").allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        // moving to a new thread
                        ioThread {

                            getInstance(context).defaultsDao().insert(
                                    DefaultsEntity(
                                    unitOfVoltage = UnitOfVoltage.V,
                                    unitOfTemperature = UnitOfTemperature.C,
                                        unitOfPower = UnitOfPower.KW,
                                        unitOfLength = UnitOfLength.M,
                                        threePhaseVoltage = Voltage(380.0, UnitOfVoltage.V),
                                        threePhasePowerFactor = PowerFactor(.8),
                                        standard = Standard.IEC,
                                        soilResistivity = ThermalResistivity(2.5,
                                            UnitOfThermalResistivity.MW),
                                        singlePhasePowerFactor = PowerFactor( .9),
                                        powerSystem = PowerSystem.SinglePhase,
                                        panelToPanelMaxVoltDrop = VoltDrop(4.0),
                                        panelToMotorMaxVoltDrop = VoltDrop(4.0),
                                        singlePhaseVoltage = Voltage(220.0, UnitOfVoltage.V),
                                        twoPhaseVoltage = Voltage(220.0, UnitOfVoltage.V),
                                        minWireSize = WireSize(1.5, UnitOfWireSize.MM2),
                                        methodOfInstallation = MethodOfInstallation.A1,
                                        maxWireSize = WireSize(240.0, UnitOfWireSize.MM2),
                                        insulation = Insulation.PVC,
                                        groundTemperature = Temperature(30.0, UnitOfTemperature.C),
                                        conductor = Conductor.Copper,
                                        circuitInTheSameConduit = CircuitCount(1),
                                        unitOfWireSize = UnitOfWireSize.MM2,
                                        ambientTemperature = Temperature(30.0, UnitOfTemperature.C),
                                        altitude = Length(1200.0, UnitOfLength.M),
                                        twoPhasePowerFactor = PowerFactor(.8)
                                ))
                        }
                    }
                })
                .build()
        }
    }
}

