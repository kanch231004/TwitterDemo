package com.example.analyticslib.batch

data class EventBatch (val batchId: Int, var eventList: ArrayList<Event>, var isSynced: Boolean )

data class Event(val event: String)


