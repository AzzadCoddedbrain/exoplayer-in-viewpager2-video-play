package com.mehuljoisar.viewpagersilder.model

data class datamodel(
    var detsils:String,
    var data : ArrayList<feedModel> = arrayListOf(),
){

}
data class feedModel(
    var name:String,
    var videoUrl:String,
    var thumb:String,
    var description:String,
)
