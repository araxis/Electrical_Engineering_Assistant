package com.vm.eea.data.project

import com.vm.eea.application.ApparentPower
import com.vm.eea.application.Current
import com.vm.eea.application.Power
import com.vm.eea.application.ReactivePower
import com.vm.eea.application.project.IGetProjectDetails
import com.vm.eea.application.project.ProjectDetails
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProjectDetails(private val db:AppDatabase) : IGetProjectDetails {

    override operator fun invoke(projectId: ProjectId):Flow<ProjectDetails>{
       return db.projectReadDao().getFullProject(projectId.id)
           .map { ProjectDetails(
               id= ProjectId(it.id),
               totalCurrent = Current(it.totalCurrent,Current.Unit.A),
               totalReactivePower = ReactivePower(it.totalReactivePower,ReactivePower.Unit.VAr),
               totalApparentPower = ApparentPower(it.totalApparentPower,ApparentPower.Unit.VA),
               totalActivePower = Power(it.totalActivePower, Power.Unit.W),
               description = it.description,
               code = it.code,
               minWireSize = it.minWireSize,
               circuitInTheSameConduit = it.circuitInTheSameConduit,
               standard = it.standard,
               threePhaseVoltage =it.threePhaseVoltageInVolt,
               altitude = it.altitude,
               soilResistivity = it.soilResistivity,
               panelToPanelMaxVoltDrop = it.panelToPanelMaxVoltDrop,
               panelToMotorMaxVoltDrop = it.panelToMotorMaxVoltDrop,
               maxWireSize = it.maxWireSize,
               insulation = it.insulation,
               conductor = it.conductor,
               groundTemperature = it.groundTemperature,
               ambientTemperature = it.ambientTemperature,
               methodOfInstallation = it.methodOfInstallation,
               twoPhaseVoltage = it.twoPhaseVoltageInVolt,
               singlePhaseVoltage = it.singlePhaseVoltageInVolt
           ) }
    }
}