package com.vm.eea.application

interface ITransactionProvider {
   suspend fun <T> runAsTransaction(block: suspend () -> T):T
}

