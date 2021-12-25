package com.vm.eea.application.bimetal

import com.vm.eea.application.ItemResult

sealed class BimetalResult{
    data class Use(val part: ItemResult<Bimetal>): BimetalResult()
    object UseLess: BimetalResult()

}
