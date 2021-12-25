package com.vm.eea.application.calculators.motorCapacitorDrive

import com.vm.eea.application.*
import com.vm.eea.application.capacitor.ICapacitorFinder
import com.vm.eea.application.capacitorContactor.ICapacitorContactorFinder
import com.vm.eea.application.protectionDevice.IFuseFinder

class MotorCapacitorDriveCalculator(private val capacitorFinder:ICapacitorFinder,
private val capacitorContactorFinder: ICapacitorContactorFinder,
private val fuseFinder:IFuseFinder) {

    suspend operator fun invoke(requiredReactivePower: ReactivePower):MotorCapacitorDriveResult{

        if(requiredReactivePower <= 0.VAr) return MotorCapacitorDriveResult.UseLess
        val kVAr=requiredReactivePower to ReactivePower.Unit.KVAr
        val capacitor=capacitorFinder.withGreaterOrEqual(kVAr)
        val capacitorContactor=capacitorContactorFinder.withGreaterOrEqual(kVAr)
        val fuseResult= if(capacitor!=null){
                val capKVAr=capacitor.reactivePower to ReactivePower.Unit.KVAr
                val current=Current(capKVAr.value * 2.6,Current.Unit.A)
                val fuse=fuseFinder.withGreaterOrEqual(current)
                 fuse.toResult()
            }else{
                    ItemResult.NotFound()
            }
        return MotorCapacitorDriveResult.Use(capacitor.toResult(),capacitorContactor.toResult(),fuseResult)

    }
}