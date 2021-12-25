package com.vm.eea.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vm.eea.application.*
import com.vm.eea.data.model.*
import com.vm.eea.data.motor.LoadEntity
import com.vm.eea.data.panel.PanelEntity
import com.vm.eea.data.panelToMotorRelation.PanelToMotorRelationEntity
import com.vm.eea.data.panelToPanelRelation.PanelToPanelRelationEntity
import com.vm.eea.data.project.ProjectEntity


@Database(entities = [
    ProjectEntity::class,
    PanelEntity::class,
    LoadEntity::class,
    PanelToPanelRelationEntity::class,
    PanelToMotorRelationEntity::class,
    WireSizeMapEntity::class],
    views = [FullMotorView::class,
    FullPanelView::class,
    FullMotorView::class,
    FullProjectView::class,
    FullMotorRelationView::class,
    FullPanelRelationView::class], version = 1)
abstract class AppDatabase : RoomDatabase(), IWriteDatabase {
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
                .build()
        }
    }
}

