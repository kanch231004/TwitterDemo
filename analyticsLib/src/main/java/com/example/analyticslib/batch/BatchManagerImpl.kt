package com.example.analyticslib.batch

import android.app.Application
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.analyticslib.persistence.EventDao
import com.example.analyticslib.persistence.UploadEventWorker
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BatchManagerImpl constructor(private val eventDao: EventDao,
                                   private val uploadWorker: UploadEventWorker,
                                   private val app: Application): BatchManager {

    private  val workManager: WorkManager by lazy { WorkManager.getInstance(app) }

    @OptIn(DelicateCoroutinesApi::class)
    override fun addEventToBatch(event: Event, ) {
        val currentBatch = eventDao.getCurrentBatch()
        GlobalScope.launch {
            currentBatch.collectLatest {
                it.eventList.add(event)
                if (isBatchReady()) {
                    val workRequest = OneTimeWorkRequestBuilder<UploadEventWorker>().build()
                    workManager.enqueue(workRequest)
                }
                eventDao.updateCurrentBatch(it)
            }
        }
        eventDao.insertEventToBatch(event)
    }

    override fun clearBatch(batchId: Int) {
        TODO("Not yet implemented")
    }

    override fun getBatchingStrategy(event: Event) {
        TODO("Not yet implemented")
    }

    override fun onBatchReady() {
        TODO("Not yet implemented")
    }

    override fun isBatchReady(): Boolean {
        TODO("Not yet implemented")
    }

}