package com.example.analyticslib.batch

interface BatchManager {
    fun addEventToBatch(event: Event)
    fun clearBatch( batchId: Int )
    fun getBatchingStrategy(event: Event)
    fun onBatchReady()

    fun isBatchReady(): Boolean

}