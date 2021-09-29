package com.vm.eea.data

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


val IO_EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}