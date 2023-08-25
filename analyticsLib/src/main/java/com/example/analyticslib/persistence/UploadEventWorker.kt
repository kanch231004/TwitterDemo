package com.example.analyticslib.persistence

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.analyticslib.api.EventUploadService

class UploadEventWorker(val context: Context, val workerParameters: WorkerParameters) : Worker(context, workerParameters){
    override fun doWork(): Result {
        TODO("Not yet implemented")
    }

}