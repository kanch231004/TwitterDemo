package com.example.analyticslib.persistence

import androidx.lifecycle.LiveData
import com.example.analyticslib.batch.Event
import com.example.analyticslib.batch.EventBatch
import kotlinx.coroutines.flow.Flow

interface EventDao {
    fun insertEventToBatch(event: Event)
    fun getCurrentBatch(): Flow<EventBatch>
    fun updateCurrentBatch(batch: EventBatch)

    fun clearBatch(batch: Int)
}