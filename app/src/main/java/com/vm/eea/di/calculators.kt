package com.vm.eea.di

import com.vm.eea.application.PanelId
import com.vm.eea.application.calculators.*
import com.vm.eea.application.protectionDevice.IAcbFinder
import com.vm.eea.application.protectionDevice.IMccbFinder
import com.vm.eea.application.protectionDevice.ITmbFinder
import com.vm.eea.application.calculators.MotorCircuitBreakerCalculator
import com.vm.eea.application.bimetal.IBimetalFinder
import com.vm.eea.application.calculators.MotorGuardCalculator
import com.vm.eea.application.calculators.applicationProject.panelProject.UpdateApplicationPanelInfo
import com.vm.eea.application.calculators.motorCapacitorDrive.MotorCapacitorDriveCalculator
import com.vm.eea.application.calculators.motorDrive.MotorDolDriveCalculator
import com.vm.eea.application.calculators.motorDrive.MotorSdDriveCalculator
import com.vm.eea.application.calculators.motorDrive.MotorSsdDriveCalculator
import com.vm.eea.application.calculators.motorDrive.MotorVsdDriveCalculator
import com.vm.eea.application.calculators.motorFullResult.MotorResultCalculator
import com.vm.eea.application.calculators.motorFullResult.PanelMotorResultCalculator
import com.vm.eea.application.calculators.panelFullResult.PanelCalculator
import com.vm.eea.application.capacitor.ICapacitorFinder
import com.vm.eea.application.capacitorContactor.ICapacitorContactorFinder
import com.vm.eea.application.contactor.IContactorFinder
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.protectionDevice.IFuseFinder
import com.vm.eea.application.ssd.ISsdFinder
import com.vm.eea.application.vsd.IVsdFinder
import com.vm.eea.data.calculators.*
import com.vm.eea.ui.calculators.main.activePowerCalculator.ActivePowerCalculatorViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.apparentCosPhi.ApparentPowerCosPhiActivePowerViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.currentImpedance.CurrentImpedanceActivePowerViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.currentResistance.CurrentResistanceActivePowerCalculatorViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveApparent.ReactiveApparentActivePowerViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveCosPhi.ReactivePowerCosPhiActivePowerViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.voltageCurrent.VoltageCurrentPowerCalculatorViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.voltageImpedance.VoltageImpedanceActivePowerCalculatorViewModel
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.ApparentPowerCalculatorViewModel
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.activeReactive.ReactiveActivePowerApparentCalculatorViewModel
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.powerCosPhi.ActivePowerCosPhiApparentPowerViewModel
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.reactiveCosPhi.ReactiveCosPhiApparentPowerViewModel
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.voltageCurrent.VoltageCurrentApparentCalculatorViewModel
import com.vm.eea.ui.calculators.main.currentCalculator.CurrentCalculatorViewModel
import com.vm.eea.ui.calculators.motor.circuitBreaker.MotorCircuitBreakerCalculatorViewModel
import com.vm.eea.ui.calculators.motor.cosPhi.MotorCosPhiCalculatorViewModel
import com.vm.eea.ui.calculators.motor.current.MotorCurrentCalculatorViewModel
import com.vm.eea.ui.calculators.motor.efficiency.MotorEfficiencyCalculatorViewModel
import com.vm.eea.ui.motor.motorResult.FullMotorCalculationResultViewModel
import com.vm.eea.ui.calculators.fullMotorCalculator.FullMotorCalculatorFormViewModel
import com.vm.eea.ui.calculators.fullPanelCalculator.PanelFullCalculatorViewModel
import com.vm.eea.ui.calculators.motor.guard.MotorGuardCalculatorViewModel
import com.vm.eea.ui.calculators.motor.power.MotorActivePowerCalculatorViewModel
import com.vm.eea.ui.calculators.motor.slip.MotorSlipCalculatorViewModel
import com.vm.eea.ui.calculators.motor.speed.MotorSpeedCalculatorViewModel
import com.vm.eea.ui.calculators.motor.startMode.MotorStartModeCalculatorViewModel
import com.vm.eea.ui.calculators.motor.torque.MotorTorqueCalculatorViewModel
import com.vm.eea.ui.calculators.motor.voltage.MotorVoltageCalculatorViewModel
import com.vm.eea.ui.motor.motorResult.LoadHtmlReportGenerator
import com.vm.eea.ui.panel.panelResult.PanelResultViewModel
import com.vm.eea.ui.home.GetCalculatorList
import com.vm.eea.ui.home.HomeViewModel
import com.vm.eea.ui.motor.addPanelMotor.AddPanelMotorViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val calculatorsModule=module{
    factory { ApparentPowerCalculator() }
    factory { ActivePowerCalculator() }
    factory { CurrentCalculator() }
    factory { ReactivePowerCalculator() }
    factory { RequiredVarCalculator() }
    factory { CapacitorCalculator() }
    factory { StartModeCalculator() }
    factory { CosPhiCalculator() }
    factory { EfficiencyCalculator() }
    factory { MotorRealSpeedCalculator() }
    factory { MotorSlipSpeedCalculator() }
    factory { MotorSlipFactorCalculator() }
    factory { MotorSynchronousSpeedCalculator() }
    factory { TorqueCalculator() }
    factory { VoltageCalculator() }
    factory<IMccbFinder> { MccbFinder() }
    factory<IAcbFinder> { AcbFinder() }
    factory<ITmbFinder> { TmbFinder() }
    factory<IBimetalFinder> { BimetalFinder() }
    factory<IFuseFinder> { FuseFinder() }
    factory<IContactorFinder> { ContactorFinder() }
    factory<ICapacitorFinder> { CapacitorFinder() }
    factory<ICapacitorContactorFinder> { CapacitorContactorFinder() }
    factory<ISsdFinder> { SsdFinder() }
    factory<IVsdFinder> { VsdFinder() }
    factory { MotorDolDriveCalculator(get()) }
    factory { MotorSdDriveCalculator(get()) }
    factory{ MotorSsdDriveCalculator(get(),get()) }
    factory { MotorVsdDriveCalculator(get()) }
    factory { MotorCapacitorDriveCalculator(get(),get(),get()) }
    factory { MotorCircuitBreakerCalculator(get(),get(),get(),get(),get()) }
    factory { PanelCircuitBreakerCalculator(get(),get(),get()) }
    factory { MotorCircuitBreakerTypeCalculator(get()) }
    factory { MotorGuardCalculator(get(),get()) }
    factory { MotorResultCalculator(get(),get(),get(),get(),get(),get(),get(),get(),get(),get(),get(),get()) }
    factory { PanelCalculator(get(),get(),get(),get()) }
    factory { PanelMotorResultCalculator(get(),get()) }
    factory { LoadHtmlReportGenerator() }
    factory { GetCalculatorList() }
    factory { UpdateApplicationPanelInfo(get(),get(),get()) }

    viewModel { CurrentCalculatorViewModel(get()) }
    viewModel{ActivePowerCalculatorViewModel()}
    viewModel { ApparentPowerCosPhiActivePowerViewModel(get()) }
    viewModel { CurrentImpedanceActivePowerViewModel(get()) }
    viewModel { CurrentResistanceActivePowerCalculatorViewModel(get()) }
    viewModel { ReactiveApparentActivePowerViewModel(get()) }
    viewModel { ReactivePowerCosPhiActivePowerViewModel(get()) }
    viewModel { VoltageCurrentPowerCalculatorViewModel(get()) }
    viewModel { VoltageImpedanceActivePowerCalculatorViewModel(get()) }
    viewModel { ReactiveActivePowerApparentCalculatorViewModel(get()) }
    viewModel { ActivePowerCosPhiApparentPowerViewModel(get()) }
    viewModel { ReactiveCosPhiApparentPowerViewModel(get()) }
    viewModel { VoltageCurrentApparentCalculatorViewModel(get()) }
    viewModel { ApparentPowerCalculatorViewModel() }
    //motor
    viewModel { MotorCosPhiCalculatorViewModel(get()) }
    viewModel { MotorCurrentCalculatorViewModel(get()) }
    viewModel { MotorEfficiencyCalculatorViewModel(get()) }
    viewModel { MotorActivePowerCalculatorViewModel(get()) }
    viewModel { MotorSlipCalculatorViewModel(get(),get()) }
    viewModel { MotorSpeedCalculatorViewModel(get(),get()) }
    viewModel { MotorStartModeCalculatorViewModel(get()) }
    viewModel { MotorTorqueCalculatorViewModel(get()) }
    viewModel { MotorVoltageCalculatorViewModel(get()) }
    viewModel { MotorCircuitBreakerCalculatorViewModel(get()) }

    viewModel { MotorGuardCalculatorViewModel(get(),get()) }
    viewModel { FullMotorCalculatorFormViewModel(get(),get(),get()) }
    viewModel { PanelFullCalculatorViewModel(get(),get(),get(),get(),get(),get()) }
    viewModel {(motorId: MotorId) -> FullMotorCalculationResultViewModel(motorId ,get(),get(),get(),get()) }
    viewModel { (panelId:PanelId)-> AddPanelMotorViewModel(panelId,get(),get()) }
    viewModel { (panelId:PanelId)-> PanelResultViewModel(panelId,get(),get(),get()) }
    viewModel{HomeViewModel(get(),get(),get(),get())}
}