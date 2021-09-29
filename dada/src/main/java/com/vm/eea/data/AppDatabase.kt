package com.vm.eea.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vm.eea.application.*
import com.vm.eea.data.model.*


@Database(entities = [
    ProjectEntity::class,
    PanelEntity::class,
    LoadEntity::class,
    PanelToPanelRelationEntity::class,
    PanelToMotorRelationEntity::class,
    DefaultsEntity::class,
    WireSizeMapEntity::class],
    views = [FullMotorView::class,
    FullPanelView::class,
    FullMotorView::class,
    FullProjectView::class,
    FullMotorRelationView::class,
    FullPanelRelationView::class], version = 1)
abstract class AppDatabase : RoomDatabase(), IWriteDatabase,IReadDatabase {
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
            return Room.databaseBuilder(context, AppDatabase::class.java, "app-db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)


                        // moving to a new thread
                        ioThread {

                            getInstance(context).defaultsDao().insert(
                                    DefaultsEntity(
                                    unitOfVoltage = Voltage.Unit.V,
                                    unitOfTemperature = UnitOfTemperature.C,
                                        unitOfPower = Power.Unit.KW,
                                        unitOfLength = Length.Unit.M,
                                        threePhaseVoltage = 380.0,
                                        threePhaseCosPhi = CosPhi(.8),
                                        standard = Standard.IEC,
                                        soilResistivity = ThermalResistivity(2.5,
                                            UnitOfThermalResistivity.MW),
                                        singlePhaseCosPhi = CosPhi( .9),
                                        powerSystem = PowerSystem.SinglePhase,
                                        panelToPanelMaxVoltDrop = VoltDrop(4.0),
                                        panelToMotorMaxVoltDrop = VoltDrop(4.0),
                                        singlePhaseVoltage = 220.0,
                                        twoPhaseVoltage =220.0,
                                        minWireSize = WireSize(1.5, UnitOfWireSize.MM2),
                                        methodOfInstallation = MethodOfInstallation.A1,
                                        maxWireSize = WireSize(240.0, UnitOfWireSize.MM2),
                                        insulation = Insulation.PVC,
                                        groundTemperature = Temperature(30.0, UnitOfTemperature.C),
                                        conductor = Conductor.Copper,
                                        circuitInTheSameConduit = CircuitCount(1),
                                        unitOfWireSize = UnitOfWireSize.MM2,
                                        ambientTemperature = Temperature(30.0, UnitOfTemperature.C),
                                        altitude = Length(1200.0, Length.Unit.M),
                                        twoPhaseCosPhi = CosPhi(.8)
                                ))
                        }
                    }
                })
                .build()
        }
    }
}

