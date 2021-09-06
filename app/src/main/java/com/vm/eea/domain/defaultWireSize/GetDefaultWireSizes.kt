package com.vm.eea.domain.defaultWireSize

import com.vm.eea.domain.UnitOfWireSize
import com.vm.eea.utilities.mm2
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetDefaultWireSizes(private val defaultIDefaultWireSizeRepository: IDefaultWireSizeRepository) {

    operator fun invoke()= flow {
        emit(listOf(
            DefaultWireSize(1.5.mm2,false),
            DefaultWireSize(2.5.mm2,false),
            DefaultWireSize(4.mm2,false),
            DefaultWireSize(6.mm2,false),
            DefaultWireSize(10.mm2,false),
            DefaultWireSize(16.mm2,false),
            DefaultWireSize(25.mm2,false),
            DefaultWireSize(35.mm2,false),
            DefaultWireSize(50.mm2,false),
            DefaultWireSize(70.mm2,false),
            DefaultWireSize(95.mm2,false),
            DefaultWireSize(120.mm2,false),
            DefaultWireSize(150.mm2,false),
            DefaultWireSize(185.mm2,false),
            DefaultWireSize(240.mm2,false),
            DefaultWireSize(300.mm2,false)))
    }
    operator fun invoke(sizeType:UnitOfWireSize) =invoke().map { it.filter { i->i.wireSize.unit==sizeType } }

}