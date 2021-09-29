package com.vm.eea.data

import com.vm.eea.data.motor.ILoadDao
import com.vm.eea.data.panel.IPanelDao
import com.vm.eea.data.panelToMotorRelation.IPanelToMotorRelationDao
import com.vm.eea.data.panelToPanelRelation.IPanelToPanelRelationDao
import com.vm.eea.data.project.IProjectDao
import com.vm.eea.data.project.IProjectPartDao

interface IWriteDatabase {
    fun projectDao(): IProjectDao
    fun panelDao(): IPanelDao
    fun defaultsDao(): IDefaultDao
    fun loadDao(): ILoadDao
    fun panelToPanelRelationDao(): IPanelToPanelRelationDao
    fun panelToMotorRelationDao(): IPanelToMotorRelationDao
}