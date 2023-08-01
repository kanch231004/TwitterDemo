package com.example.twitterdemo.data

import com.google.gson.annotations.SerializedName
data class TweetModel(
    @SerializedName("user") var user: User,
    @SerializedName("postedAt") var postedAt: String,
    @SerializedName("content") var content: String,
    @SerializedName("profileIcon") var profileIcon: String,
    @SerializedName("action") var action: Action
)
data class User(
    @SerializedName("name") var name: String?,
    @SerializedName("handle") var handle: String
)

//All numbers can be Int from server itself including views and can be parsed to redable format
data class Action(
    @SerializedName("comments") val comments: String, @SerializedName("reposts") val reposts: Int,
    @SerializedName("likes") var likes: Int, @SerializedName("views") var views: String
)
data class TweetResponse(
    @SerializedName("totalResults") var totalResults: Int,
    @SerializedName("posts") var posts: ArrayList<TweetModel>
)
