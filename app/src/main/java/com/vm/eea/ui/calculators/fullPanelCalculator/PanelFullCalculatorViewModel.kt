package com.vm.eea.ui.calculators.fullPanelCalculator

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Voltage
import com.vm.eea.application.calculators.applicationProject.panelProject.*
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.motor.addMotor.AddMotor
import com.vm.eea.ui.*
import com.vm.eea.utilities.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PanelFullCalculatorViewModel(
    private val getApplicationPanel: IGetApplicationPanel,
    private val getPanelMotors: IGetPanelMotors,
    private val motorRepository:IMotorRepository,
    private val addMotor: AddMotor,
    private val navigationManager: NavigationManager,
    private val updateApplicationPanelInfo: UpdateApplicationPanelInfo):ContainerHost<UiState,Effect> ,ViewModel() {
    lateinit var panel: ApplicationPanel
    override val container: Container<UiState, Effect>
        = container(UiState()){
            onIO {
                getApplicationPanel().flatMapMerge { panel->
                    getPanelMotors(panel.panelId).map { panel to it }
                }.collect {
                    panel=it.first
                    intent {
                        val newState=state.copy(
                            lineLineVoltage = VoltageField.valid(panel.lineToLineVoltage),
                            lineNullVoltage = VoltageField.valid(panel.lineToNullVoltage),
                            coincidenceFactor = CoincidenceFactorField.valid(value=panel.coincidenceFactor),
                            demandFactor = CosPhiField.valid(value=panel.demandFactor),
                            loads = it.second,
                            panelId = panel.panelId
                        )
                        updateCalculationState(newState)
                    }
                }


            }
    }



    fun onMotorClicked(item: PanelMotor)=intent{
        navigationManager.navigate(MotorDestinations.UpdateMotor(item.motorId))
    }

    fun showMotorReports(motorId: MotorId){
        if(motorId.id<=0) return
            navigationManager.navigate(MotorCalculatorDestinations.FullMotorResult(motorId))
    }

    fun showPanelReports()=intent{
        if(isValid(state)){
            val appPanel=mapToApplicationPanel(state)

             updateApplicationPanelInfo(appPanel)

       navigationManager.navigate(MotorCalculatorDestinations.FullPanelResult(state.panelId))
          //  navigationManager.navigate(ReportDestinations.ReportViewer(state.panelId))
        }
    }

    fun removeMotor(motorId:MotorId)=onIO{
        motorRepository.remove(motorId)
    }
    fun editMotor(motorId: MotorId){
         navigationManager.navigate(MotorDestinations.UpdateMotor(motorId))
    }

    fun onLineNullVoltageChange(value:String,unit: Voltage.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val voltageState=state.lineNullVoltage.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(lineNullVoltage = voltageState)
        updateCalculationState(newState)
    }
    fun onLineLineVoltageChange(value:String,unit: Voltage.Unit)=intent{
        val validationResult= Validator.validate.positiveNumber(value,"")
        val voltageState=state.lineLineVoltage.copy(input=value,unit = unit,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(lineLineVoltage = voltageState)
        updateCalculationState(newState)
    }

    fun onCoincidenceFactorChange(value:String)=intent{
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val fieldState=state.coincidenceFactor.copy(input= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(coincidenceFactor = fieldState)
        updateCalculationState(newState)
    }
    fun onDemandFactorChange(value: String)=intent {
        val validationResult= Validator.validate.inRange(value,.1,1.0,"")
        val fieldState=state.demandFactor.copy(input= value,isValid = validationResult==null,error = validationResult)
        val newState= state.copy(demandFactor = fieldState)
        updateCalculationState(newState)
    }

    private fun isValid(state:UiState):Boolean{
        if(state.lineLineVoltage.notValid) return false
        if(state.lineNullVoltage.notValid) return false
        if(state.demandFactor.notValid) return false
        if(state.coincidenceFactor.notValid) return false

        return true
    }

    private fun updateCalculationState(newState:UiState)=intent {
        val canCalculate=isValid(newState)
        val formState=newState.copy(canCalculate = canCalculate)
        reduce { formState }
    }

    private fun mapToApplicationPanel(state:UiState):ApplicationPanel{
        val lineLineVoltage=state.lineLineVoltage.value
        val lineNullVoltage=state.lineNullVoltage.value
        val demandFactor=state.demandFactor.value
        val coincidenceFactor=state.coincidenceFactor.value
        return ApplicationPanel(state.panelId,panel.projectId,lineNullVoltage,lineLineVoltage,coincidenceFactor,demandFactor)
    }

    fun onAddMotor()=intent{
       navigationManager.navigate(MotorCalculatorDestinations.AddPanelMotor(state.panelId))
    }

    fun onMotorMenuClicked(item: PanelMotor)=intent {
        reduce { state.copy(currentMotorId = item.motorId) }
        postSideEffect(Effect.ShowMotorMenu())
    }

    fun addCopy(motorId: MotorId)=intent{
        addMotor.addCopy(motorId ,state.panelId)
    }


}