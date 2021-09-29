package com.vm.eea.data

import com.vm.eea.data.motor.IMotorReadDao
import com.vm.eea.data.panel.IPanelReadDao
import com.vm.eea.data.panelToMotorRelation.IPanelToMotorRelationReadDao
import com.vm.eea.data.panelToPanelRelation.IPanelToPanelRelationReadDao
import com.vm.eea.data.project.IProjectReadDao

interface IReadDatabase {

    fun projectReadDao(): IProjectReadDao
    fun panelToPanelReadDao(): IPanelToPanelRelationReadDao
    fun panelToMotorReadDao():IPanelToMotorRelationReadDao
    fun motorReadDao():IMotorReadDao
    fun panelRadDao():IPanelReadDao
}