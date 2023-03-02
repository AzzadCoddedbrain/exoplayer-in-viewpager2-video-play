package com.mehuljoisar.viewpagersilder

import NestedAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehuljoisar.viewpagersilder.databinding.ActivityNestedRecyclerviewBinding
import com.mehuljoisar.viewpagersilder.model.Video
import com.mehuljoisar.viewpagersilder.model.sampleDataModel

class NestedRecyclerviewActivity : AppCompatActivity() {

    lateinit var binding: ActivityNestedRecyclerviewBinding


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNestedRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<String>()

        list.add("this is test")
        list.add("this is test")
        list.add("this is test")
        list.add("this is test")
        list.add("this is test")
        list.add("this is test")




        var sublist  = ArrayList<Video>()
        var sublist1  = ArrayList<Video>()
        var sublist2  = ArrayList<Video>()

        sublist.clear()
        sublist.add(Video("intro ","https://mobstatus.com/wp-content/uploads/2023/01/mahadev-status-video-shiv-status-video-mahakal-video-_shorts-_short-_viral720P_HD.mp4"))
        sublist.add(Video("GOd ","https://mobstatus.com/wp-content/uploads/2022/12/Mahadev-status-video-bholenath-status-%E2%98%98%EF%B8%8F-mahakal-status-_mahadev720P_HD.mp4"))
        sublist.add(Video("New Student ","https://mobstatus.com/wp-content/uploads/2022/12/Jab-girte-hue-Maine-Tera-Naam-Liya-Hai-Radha-Krishna-Status-__-Radha-Krishna-4k-Full-Screen-status720P_HD.mp4"))


        sublist1.clear()
        sublist1.add(Video("God is Amazing","https://mobstatus.com/wp-content/uploads/2022/12/Bholenath-4k-shayari-status-%E2%9C%A8-%E2%9D%A3%EF%B8%8F-_-Mahakal-WhatsApp-status-full-screen-video-_harharmahadev720P_HD.mp4"))
        sublist1.add(Video("Now is Happy","https://mobstatus.com/wp-content/uploads/2022/05/Shree-Krishna-Ji-Ki-Bhakti-Status-Video.mp4"))
        sublist1.add(Video("Cool","https://mobstatus.com/wp-content/uploads/2022/05/Jay-Murlidhar-Bhagwan-Ki-Puja-Status-Video.mp4"))

        sublist2.clear()
        sublist2.add(Video("Nice View","https://mobstatus.com/wp-content/uploads/2022/05/Mahakali-Maa-Ji-Ki-Bhakti-Special-Short-Clip-Video-Status.mp4"))
        sublist2.add(Video("this is pinnt","https://mobstatus.com/wp-content/uploads/2022/05/Strogest-God-From-Hindu-Mythology-Bhakti-30sec-Video-Status.mp4"))
        sublist2.add(Video("Ram Ram ji","https://mobstatus.com/wp-content/uploads/2022/05/Ganesh-Ji-Mantra-Aarti-Bhakti-WhatsApp-Status-Video.mp4"))


        var data  = ArrayList<sampleDataModel>()
            data.clear()
            data.add(sampleDataModel("Are You Looking For God Status Videos :- Shree Krishna Status Video New, Hindu Bhagwan Bhakti Version Full Screen Stories Video, Devi Maa Hindi Bhakti Song 4k Full Screen 30sec Video Status, Mahadev Status Video, So Just Go And Download Your Favourite Video Which One You Liked Most And Put It On Your Facebook And Whatsapp Status Video. And Also, Don’t Forget To Share This Content With Your Friends, Family And Loving One…",sublist))
            data.add(sampleDataModel("We Hope You Will Like This Content What You’re Searching For God Status Video Are You Really Like Our Status Video So Please Don’t Forget To Share With Your Friends, Family Or Loving One. Thanks For Visiting Mobstatus.Com",sublist1))
            data.add(sampleDataModel("Are You Looking For Ladu Gopal Status Video :- Laddu Gopal Temple 4k Status Video, Laddu Gopal Making Full Screen Status Video, Laddu Gopal Shringar Short Status Video, Laddu Gopal Dialogue Status Video, Laddu Gopal Motivation Status Video, Laddu Gopal Love Status Video, Laddu Gopal Romantic 30sec Status Video, Lord Laddu Gopal Ultra HD Status Video, So Just Go And Download Your Favourite Video Which One You Liked Most And Put It On Your Facebook And Whatsapp Status Video. And Also, Don’t Forget To Share This Content With Your Friends, Family And Loving One.",sublist2))


        val adapter = NestedAdapter(data,this)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        binding.recyclerview.setRecyclerListener {

        }



    }
}