package com.example.analyticslib.repository

import com.example.analyticslib.batch.Event

interface  EventRepository {
    fun insertEventToBatch(event: Event)
    fun uploadEventToService(event: Event)
}