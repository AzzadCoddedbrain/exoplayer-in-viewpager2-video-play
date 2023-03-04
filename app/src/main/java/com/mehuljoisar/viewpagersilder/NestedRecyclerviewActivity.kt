package com.mehuljoisar.viewpagersilder

import NestedAdapter
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mehuljoisar.viewpagersilder.databinding.ActivityNestedRecyclerviewBinding
import com.mehuljoisar.viewpagersilder.model.Video
import com.mehuljoisar.viewpagersilder.model.FeedDataModel


class NestedRecyclerviewActivity : AppCompatActivity() {

    lateinit var binding: ActivityNestedRecyclerviewBinding
    lateinit var adapter: NestedAdapter
    var data = ArrayList<FeedDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNestedRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var sublist = ArrayList<Video>()
        var sublist1 = ArrayList<Video>()
        var sublist2 = ArrayList<Video>()

        sublist.clear()
        sublist.add(
            Video(
                "intro ",
                "https://mobstatus.com/wp-content/uploads/2023/01/mahadev-status-video-shiv-status-video-mahakal-video-_shorts-_short-_viral720P_HD.mp4"
            )
        )
        sublist.add(
            Video(
                "GOd ",
                "https://mobstatus.com/wp-content/uploads/2022/12/Mahadev-status-video-bholenath-status-%E2%98%98%EF%B8%8F-mahakal-status-_mahadev720P_HD.mp4"
            )
        )
        sublist.add(
            Video(
                "New Student ",
                "https://mobstatus.com/wp-content/uploads/2022/12/Jab-girte-hue-Maine-Tera-Naam-Liya-Hai-Radha-Krishna-Status-__-Radha-Krishna-4k-Full-Screen-status720P_HD.mp4"
            )
        )


        sublist1.clear()
        sublist1.add(
            Video(
                "God is Amazing",
                "https://mobstatus.com/wp-content/uploads/2022/12/Bholenath-4k-shayari-status-%E2%9C%A8-%E2%9D%A3%EF%B8%8F-_-Mahakal-WhatsApp-status-full-screen-video-_harharmahadev720P_HD.mp4"
            )
        )
        sublist1.add(
            Video(
                "Now is Happy",
                "https://mobstatus.com/wp-content/uploads/2022/05/Shree-Krishna-Ji-Ki-Bhakti-Status-Video.mp4"
            )
        )
        sublist1.add(
            Video(
                "Cool",
                "https://mobstatus.com/wp-content/uploads/2022/05/Jay-Murlidhar-Bhagwan-Ki-Puja-Status-Video.mp4"
            )
        )

        sublist2.clear()
        sublist2.add(
            Video(
                "Nice View",
                "https://mobstatus.com/wp-content/uploads/2022/05/Mahakali-Maa-Ji-Ki-Bhakti-Special-Short-Clip-Video-Status.mp4"
            )
        )
        sublist2.add(
            Video(
                "this is pinnt",
                "https://mobstatus.com/wp-content/uploads/2022/05/Strogest-God-From-Hindu-Mythology-Bhakti-30sec-Video-Status.mp4"
            )
        )
        sublist2.add(
            Video(
                "Ram Ram ji",
                "https://mobstatus.com/wp-content/uploads/2022/05/Ganesh-Ji-Mantra-Aarti-Bhakti-WhatsApp-Status-Video.mp4"
            )
        )


        data.clear()
        data.add(FeedDataModel("video details ", sublist))
        data.add(FeedDataModel("demo video details ", sublist1))
        data.add(FeedDataModel("", sublist2))


        adapter = NestedAdapter(data, this)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter


        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val pos =
                    (recyclerView.layoutManager as? LinearLayoutManager)?.findLastCompletelyVisibleItemPosition()

                if (pos == -1) return

                Log.d(TAG, "onScrolled: Scrolled $pos")


//                val layoutManager: RecyclerView.LayoutManager? = binding.recyclerview.getLayoutManager()
//                if (layoutManager is LinearLayoutManager) {
//                    // Scroll to item and make it the first visible item of the list.
//                    if (pos != null) {
//                        layoutManager.scrollToPositionWithOffset(pos, 0)
//                    }
//                } else {
//                    if (pos != null) {
//                        binding.recyclerview.smoothScrollToPosition(pos)
//                    }
//                }



                /* if (posts[pos!!].messageType == "video" || posts[pos].messageType == "messageVideo") { // you might remove this line If you only use video players. But, I needed it.
                     posts[pos].playVideo(posts[pos].playerView!!)
                     Log.d(TAG, "onScrolled: played")
                 }*/


                Log.d(TAG, "onScrolled: startCheckPauseTask")
                for (position in data.indices) {
                    if (position == pos) continue

                    /*   data[position].playerView?.pause()
                       posts[position].playerView?.seekTo(0)*/
                }
                Log.d(TAG, "onScrolled: finishCheckPauseTask")

            }
        })



    }


    override fun onPause() {
        super.onPause()
        adapter.onPauseAllVideo()
    }

    override fun onStop() {
        super.onStop()
        adapter.onPauseAllVideo()
    }

    override fun onResume() {
        super.onResume()
        adapter.onResumeAllVideo()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.onPauseAllVideo()
    }


}