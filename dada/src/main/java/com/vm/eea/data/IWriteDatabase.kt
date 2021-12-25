package com.vm.eea.data

import com.vm.eea.data.motor.IMotorWriteDao
import com.vm.eea.data.panel.IPanelDao
import com.vm.eea.data.panelToMotorRelation.IPanelToMotorRelationDao
import com.vm.eea.data.panelToPanelRelation.IPanelToPanelRelationDao
import com.vm.eea.data.project.IProjectDao

interface IWriteDatabase {
    fun projectDao(): IProjectDao
    fun panelDao(): IPanelDao
    fun loadDao(): IMotorWriteDao
    fun panelToPanelRelationDao(): IPanelToPanelRelationDao
    fun panelToMotorRelationDao(): IPanelToMotorRelationDao
}