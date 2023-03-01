package com.mehuljoisar.viewpagersilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.mehuljoisar.viewpagersilder.databinding.ActivityMainBinding
import com.mehuljoisar.viewpagersilder.model.Video

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: VideoAdapter

    private val videos = ArrayList<Video>()
    private val exoplayerItems= ArrayList<VideoAdapter.ExoplayerItem>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        videos.clear()
        videos.add(
            Video(
                "my code","https://www.statuslagao.com/whatsapp/videos/hanuman/hanuman-status-002.mp4"
            )
        )
        videos.add(
            Video(
                "my code","https://www.statuslagao.com/whatsapp/videos/hanuman/hanuman-status-002.mp4"
            )
        )
        videos.add(
            Video(
                "my code","https://www.statuslagao.com/whatsapp/videos/hanuman/hanuman-status-002.mp4"
            )
        )
        videos.add(
            Video(
                "my code","https://www.statuslagao.com/whatsapp/videos/hanuman/hanuman-status-002.mp4"
            )
        )
        videos.add(
            Video(
                "my code","https://www.statuslagao.com/whatsapp/videos/hanuman/hanuman-status-002.mp4"
            )
        )


        adapter = VideoAdapter(this,videos,object : VideoAdapter.OnVideoPreparedListner{
            override fun onVideoPrepared(exoPlayerItem: VideoAdapter.ExoplayerItem) {
                exoplayerItems.add(exoPlayerItem)
            }
        })
        binding.viewpager2.adapter = adapter

        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val previesIndex = exoplayerItems.indexOfFirst { it.exoplayer.isPlaying }
                if (previesIndex != -1){
                    val player = exoplayerItems[previesIndex].exoplayer
                    player.pause()
                    player.playWhenReady = false
                }
                val newIndex = exoplayerItems.indexOfFirst { it.position == position }
                if (newIndex != -1){
                    val player = exoplayerItems[newIndex].exoplayer
                    player.playWhenReady = true
                    player.play()
                }
            }
        })


    }

    override fun onPause() {
        super.onPause()
        val index = exoplayerItems.indexOfFirst { it.position==binding.viewpager2.currentItem }
        if (index != -1){
            val player = exoplayerItems[index].exoplayer
            player.pause()
            player.playWhenReady = false
        }
    }


    override fun onResume() {
        super.onResume()
        val index = exoplayerItems.indexOfFirst { it.position==binding.viewpager2.currentItem }
        if (index != -1){
            val player = exoplayerItems[index].exoplayer
            player.playWhenReady = true
            player.play()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (exoplayerItems.isNotEmpty()){
            for (i in exoplayerItems){
                val player = i.exoplayer
                player.stop()
                player.clearMediaItems()
            }
        }
    }


}