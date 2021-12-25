package com.vm.eea.ui.motor.motorResult

import androidx.lifecycle.ViewModel
import com.vm.eea.application.bimetal.BimetalResult
import com.vm.eea.application.calculators.motorCapacitorDrive.MotorCapacitorDriveResult
import com.vm.eea.application.calculators.motorDrive.*
import com.vm.eea.application.calculators.motorFullResult.MotorCalculationResult
import com.vm.eea.application.calculators.motorFullResult.MotorResultCalculator
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.calculators.applicationProject.motorProject.ApplicationMotor
import com.vm.eea.application.calculators.applicationProject.motorProject.IGetApplicationMotor
import com.vm.eea.ui.GroupPropertyItem
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.PropertyItem
import com.vm.eea.utilities.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FullMotorCalculationResultViewModel(private val motorId: MotorId,
                                          private val getApplicationMotor:IGetApplicationMotor,
                                          private val reportGenerator: LoadHtmlReportGenerator,
                                          private val motorResultCalculator: MotorResultCalculator,
                                          private val navigationManager: NavigationManager
):ContainerHost<MotorResultState, Effect>,ViewModel() {

    override val container: Container<MotorResultState, Effect>
         = container(MotorResultState()){
             onIO {
                 val motor=getApplicationMotor(motorId)
                 calculate(motor)

             }
    }

    fun calculate(motor: ApplicationMotor)=intent {
        val power=motor.power
        val voltage=motor.voltage
        val startMode=motor.startMode
        val efficiency=motor.efficiency
        val cosPhi=motor.powerfactor
        val demandFactor=motor.demandFactor
        val overLoadRelay=motor.hasOverLoadRelay
        val isBiDirect=motor.isBiDirect
        val ratedSpeed=motor.ratedSpeed
        val protectionType=motor.protectionType
        val system=motor.system
        val result=motorResultCalculator(power,voltage,efficiency,cosPhi,
            ratedSpeed,demandFactor,startMode,protectionType,overLoadRelay,isBiDirect,system)


        val items=mapToPropertyList(motor,result)
        val report=reportGenerator.generate(items)
        reduce { state.copy(resultItems = items,report = report) }

    }



    private fun mapToPropertyList(motor: ApplicationMotor, result: MotorCalculationResult): MutableList<GroupPropertyItem> {
        val cat1="Calculation result"
        val spec="Specification"
        val protections="Protections"
        val specItems=listOf(
            motor.power.propertyItem("Power",spec),
            motor.voltage.propertyItem("Voltage",spec),
            motor.system.propertyItem("Current type",spec),
            motor.powerfactor.propertyItem(CosPhi,spec),
            motor.demandFactor.propertyItem("Demand factor",spec),
            motor.efficiency.propertyItem("Efficiency",spec),
            motor.startMode.propertyItem("Start mode",spec),
            motor.ratedSpeed.propertyItem("Rated speed",spec),
            PropertyItem("Bidirectional",value = motor.isBiDirect.toString(),category = spec)

        )
        val calculations= mutableListOf(
            result.current.propertyItem("Current",cat1),

            result.apparentPower.propertyItem("Apparent input",cat1),
            result.reactivePower.propertyItem("Reactive input",cat1),
            result.torque.propertyItem("Torque",cat1),
            result.requiredReactivePower.propertyItem("Required kVar",cat1),
        )
        val protectionsResult= mutableListOf( result.protection.propertyItem("Circuit breaker",protections))
        when(val a=result.overLoadProtection){
            is BimetalResult.Use -> protectionsResult.add(PropertyItem("Over load relay",a.part.display({it.displayRange()}),category = protections))
            BimetalResult.UseLess -> {   }
        }
        val ret= mutableListOf(GroupPropertyItem(spec,value = spec,specItems ),
            GroupPropertyItem(key=cat1,value = cat1, calculations),
            GroupPropertyItem(key = protections,protections,protectionsResult))

        ret.add(toPropertyItems(result.powerDrive))
        ret.add(toPropertyItems(result.capacitor))


        return ret
    }

    private fun toPropertyItems(capResult: MotorCapacitorDriveResult): GroupPropertyItem {
        val category="Capacitor parts"
        val items= when(capResult){
            is MotorCapacitorDriveResult.Use -> listOf(
                capResult.capacitor.propertyItem("Capacitor",category,{it.reactivePower.toFormatString()}),
                capResult.capacitorContactor.propertyItem("Capacitor contactor",category,{it.reactivePower.displayOrZero()}),
                capResult.fuse.propertyItem("Fuse",category,{it.current.displayOrZero()}),
            )
            MotorCapacitorDriveResult.UseLess -> emptyList()
        }
        return GroupPropertyItem(key=category,value = category,items)
    }

    private fun toPropertyItems(result: IMotorPowerDriveResult): GroupPropertyItem {
        val category="Power drive"
        val items= when(result){
            is BiDirectMotorSsdDriveResult -> listOf(
                result.leftContactor.propertyItem("C1",category,{it.current.displayOrZero()}),
                result.rightContactor.propertyItem("C2",category,{it.current.displayOrZero()}),
                result.ssd.propertyItem("Ssd",category,{it.power.displayOrZero()})
            )
            is BiDirectSdDriveResult -> listOf(
                result.mainContactor.propertyItem("Line",category,{it.current.displayOrZero()}),
                result.deltaContactor.propertyItem("delta",category,{it.current.displayOrZero()}),
                result.starContactor.propertyItem("star",category,{it.current.displayOrZero()}),
                result.biDirectContactor.propertyItem("bi direct",category,{it.current.displayOrZero()})
            )
            is MotorBiDirectDolDriveResult -> listOf(
                result.mainContactor.propertyItem("C1",category,{it.current.displayOrZero()}),
                result.biDirectContactor.propertyItem("bi direct",category,{it.current.displayOrZero()})
            )
            is MotorDolDriveResult -> listOf(
                result.mainContactor.propertyItem("C1",category,{it.current.displayOrZero()})
            )
            is MotorVsdResult -> listOf(
                result.vsd.propertyItem("Vsd",category,{it.power.displayOrZero()})
            )
            is SdDriveResult -> listOf(
                result.mainContactor.propertyItem("Line",category,{it.current.displayOrZero()}),
                result.deltaContactor.propertyItem("delta",category,{it.current.displayOrZero()}),
                result.starContactor.propertyItem("star",category,{it.current.displayOrZero()})
            )
            is SsdDriveResult -> listOf(
                result.ssd.propertyItem("Ssd",category,{it.power.displayOrZero()})
            )
        }
        return GroupPropertyItem(key=category,value = category,items)
    }

    fun share()=intent{
        postSideEffect(Effect.Share())
    }
    fun preview()=intent{
        reduce { state.copy(preview = true) }
    }
    fun onBack()=intent {
      if(state.preview){
          reduce { state.copy(preview = false) }
      }else{
          navigationManager.back()
      }
    }


}