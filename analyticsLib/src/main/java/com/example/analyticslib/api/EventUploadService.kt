package com.example.analyticslib.api

import com.example.analyticslib.batch.EventBatch

interface EventUploadService {

    suspend fun uploadEvent()
    suspend fun  uploadBatchedEvents(batch: EventBatch)
}