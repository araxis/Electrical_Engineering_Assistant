package com.vm.eea.domain

interface ITransactionProvider {
   suspend fun <T> runAsTransaction(block: suspend () -> T):T
}

