package com.vm.eea.domain.defaultWireSize

import com.vm.eea.domain.ITransactionProvider


class AddNewDefaultWireSize(private val transactionProvider: ITransactionProvider,
                            private val defaultIDefaultWireSizeRepository: IDefaultWireSizeRepository) {

    suspend operator fun invoke(item: DefaultWireSize){
        transactionProvider.runAsTransaction {
            val isExist=defaultIDefaultWireSizeRepository.isExist(item.wireSize)
            if(isExist) return@runAsTransaction
            defaultIDefaultWireSizeRepository.insert(item)
        }
    }
}