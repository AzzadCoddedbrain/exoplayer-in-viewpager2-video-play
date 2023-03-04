package com.mehuljoisar.viewpagersilder

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.mehuljoisar.viewpagersilder.databinding.ParentLayoutBinding
import com.mehuljoisar.viewpagersilder.model.Video


class VideoAdapter(var context: Context, var videos: ArrayList<Video>,var  videoPreparedListner: OnVideoPreparedListner) :
    RecyclerView.Adapter<VideoAdapter.VideoVideoHolder>() {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var mediaSource: MediaSource
    class VideoVideoHolder(var binding: ParentLayoutBinding, var context: Context,var videoPreparedListner: OnVideoPreparedListner):
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoVideoHolder {
        val view = ParentLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return VideoVideoHolder(view, context,videoPreparedListner)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: VideoVideoHolder,position: Int) {
        val model = videos[position]
        videoSetup(holder.binding,holder.absoluteAdapterPosition,model.url.trim())
    }



    private fun videoSetup(binding: ParentLayoutBinding, position: Int, url: String) {
        exoPlayer = ExoPlayer.Builder(context).build()
        exoPlayer.addListener(object : Player.Listener {

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                super.onPlayerStateChanged(playWhenReady, playbackState)
                if (playbackState == Player.STATE_BUFFERING) {
                    Log.e("TAG", "onPlayerStateChanged: BuFFRING")
                } else if (playbackState == Player.STATE_READY) {
                    Log.e("TAG", "onPlayerStateChanged: READY")
                }
            }
        })
        binding.playerview.player = exoPlayer
        exoPlayer.seekTo(0)
        exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

        val dataSource = DefaultDataSource.Factory(context)
        mediaSource = ProgressiveMediaSource.Factory(dataSource).createMediaSource(MediaItem.fromUri(Uri.parse(url)))

        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()

        var absoluteAdapterPosition = position
        if (absoluteAdapterPosition == 0) {
            exoPlayer.playWhenReady = true
            exoPlayer.pause()
        }else{
            exoPlayer.playWhenReady = false
            exoPlayer.pause()
        }

        videoPreparedListner.onVideoPrepared(ExoplayerItem(exoPlayer,absoluteAdapterPosition))
    }
    override fun onViewRecycled(holder: VideoVideoHolder) {
        if (holder.binding.playerview.player != null) {
            holder.binding.playerview.player?.release()
        }
        super.onViewRecycled(holder)
    }

    interface OnVideoPreparedListner {
        fun onVideoPrepared(exoPlayerItem: ExoplayerItem)

    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        releasePlayer()

    }

/*
    override fun onViewDetachedFromWindow(holder: VideoHolder) {
        super.onViewDetachedFromWindow(holder)
        if (exoplayerItems.isNotEmpty()){
            for (i in exoplayerItems){
                val player = i.exoplayer
                player.pause()
            }
        }
    }

    override fun onViewAttachedToWindow(holder: VideoHolder) {
        super.onViewAttachedToWindow(holder)
        val index = exoplayerItems.indexOfFirst { it.position==holder.binding.viewpager2.currentItem }
        if (index != -1){
            val player = exoplayerItems[index].exoplayer
//            player.playWhenReady = true
//            player.play() // auto play
            player.pause() // not auot play
            Log.e("TAG", "onViewAttachedToWindow: -- > play "+holder.position )
        }
    }*/

    override fun onViewAttachedToWindow(holder: VideoVideoHolder) {
        super.onViewAttachedToWindow(holder)
//        val index = exoplayerItems.indexOfFirst { holder.adapterPosition==holder.binding.playerview.player }
        if (holder.adapterPosition != -1){
            val player = exoPlayer
//            player.playWhenReady = true
//            player.play() // auto play
            player.pause() // not auot play
            Log.e("TAG", "onViewAttachedToWindow: -- > play "+holder.position )
        }

    }

    override fun onViewDetachedFromWindow(holder: VideoVideoHolder) {
        super.onViewDetachedFromWindow(holder)
        if (holder.adapterPosition != -1){
            val player = exoPlayer
//            player.playWhenReady = true
//            player.play() // auto play
            player.pause() // not auot play
            Log.e("TAG", "onViewAttachedToWindow: -- > play "+holder.position )
        }
    }


    class ExoplayerItem(
        var exoplayer: ExoPlayer,
        var position:Int) {
    }
     fun releasePlayer() {
        exoPlayer.let {
            it.currentPosition
            it.currentWindowIndex
            it.playWhenReady
            it.release()
            it.stop()
        }
    }


}