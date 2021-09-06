package com.vm.eea.domain.defaultWireSize

import com.vm.eea.ITransactionProvider
import com.vm.eea.withTransaction

class AddNewDefaultWireSize(private val transactionProvider: ITransactionProvider,
                            private val defaultIDefaultWireSizeRepository: IDefaultWireSizeRepository) {

    suspend operator fun invoke(item: DefaultWireSize){
        transactionProvider.withTransaction {
            val isExist=defaultIDefaultWireSizeRepository.isExist(item.wireSize)
            if(isExist) return@withTransaction
            defaultIDefaultWireSizeRepository.insert(item)
        }
    }
}