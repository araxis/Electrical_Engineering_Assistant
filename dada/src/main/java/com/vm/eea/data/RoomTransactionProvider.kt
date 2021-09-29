package com.vm.eea.data

import androidx.room.withTransaction
import com.vm.eea.application.ITransactionProvider

class RoomTransactionProvider(private val db: AppDatabase): ITransactionProvider {
    override suspend fun <T> runAsTransaction(block: suspend () -> T): T =
        db.withTransaction(block)
}

//suspend fun<T> ITransactionProvider.withTransaction(block: suspend () -> T): T =runAsTransaction(block)