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
data class Action(
    @SerializedName("comments") val comments: String, @SerializedName("reposts") val reposts: String,
    @SerializedName("likes") var likes: String, @SerializedName("views") var views: String
)
data class TweetResponse(
    @SerializedName("totalResults") var totalResults: Int,
    @SerializedName("posts") var posts: ArrayList<TweetModel>
)
