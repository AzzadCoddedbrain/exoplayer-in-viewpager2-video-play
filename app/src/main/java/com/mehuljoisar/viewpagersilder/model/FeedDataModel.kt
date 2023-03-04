package com.mehuljoisar.viewpagersilder.model

data class FeedDataModel(var comment:String, var videolist: ArrayList<Video>)
data class Video(
    var title:String,
    var url:String
)
