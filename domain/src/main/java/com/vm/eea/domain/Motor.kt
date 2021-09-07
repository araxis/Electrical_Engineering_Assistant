package com.vm.eea.domain

import com.vm.eea.domain.*

data class Motor(val code:String,
                 val description:String,
                 val power: Power,
                 val powerfactor: PowerFactor,
                 val demandFactor: PowerFactor,
                 val efficiency: Efficiency,
                 val system: PowerSystem,
                 val serviceMode: ServiceMode,
                 val projectId:Long,
                 val powerSupplyPath: SupplyPath,
                 val feedingMode: FeedingMode,
                 val id: LoadId
                  )
