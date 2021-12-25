package com.vm.eea.data.panel

import com.vm.eea.application.CosPhi
import com.vm.eea.application.PanelId
import com.vm.eea.application.Voltage
import com.vm.eea.application.calculators.applicationProject.motorProject.IApplicationProjectCodeResolver
import com.vm.eea.application.calculators.applicationProject.panelProject.ApplicationPanel
import com.vm.eea.application.calculators.applicationProject.panelProject.IGetApplicationPanel
import com.vm.eea.application.panel.CoincidenceFactor
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetApplicationPanel(private val codeResolver: IApplicationProjectCodeResolver,
private val db:AppDatabase):IGetApplicationPanel {
    override  fun invoke(): Flow<ApplicationPanel> {
        val projectCode=codeResolver.panelProjectCode()
        val entity= db.panelDao().getApplicationPanelFlow(projectCode)
            return entity.map {  ApplicationPanel(PanelId(it.panelId),
                ProjectId(it.projectId), Voltage(it.lineNullVoltage,Voltage.Unit.V),
            Voltage(it.lineLineVoltage,Voltage.Unit.V), CoincidenceFactor(it.coincidenceFactor),
                CosPhi(it.demandFactor))}
    }
}