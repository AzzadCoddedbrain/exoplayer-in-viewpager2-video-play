package com.mehuljoisar.viewpagersilder

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.*
import android.widget.Toast
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
        fun     setVideoPath(url: String) {
            /*exoPlayer = ExoPlayer.Builder(context).build()
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

            if (absoluteAdapterPosition == 0) {
                exoPlayer.playWhenReady = true
//                exoPlayer.play()
                exoPlayer.pause()
            }else{
                exoPlayer.playWhenReady = false
                exoPlayer.pause()
            }

            videoPreparedListner.onVideoPrepared(ExoplayerItem(exoPlayer,absoluteAdapterPosition))*/

        }

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
        holder.setVideoPath(model.url.trim())
        /*
        holder.binding.playerview.setOnClickListener(){
            it.setOnTouchListener(object : View.OnTouchListener {
                val gestureDetector: GestureDetector = GestureDetector(context, object:
                    GestureDetector.SimpleOnGestureListener() {

                    override fun onSingleTapUp(e: MotionEvent): Boolean {
                        super.onSingleTapUp(e)

                        *//*if (!player.playWhenReady) {
                            player.playWhenReady = true
                        } else {
                            player.playWhenReady = false
                        }*//*
                        holder.binding.playerview.player!!.playWhenReady = holder.binding.playerview.player!=null


                        return true
                    }
                })

                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    if (p1 != null) {
                        gestureDetector.onTouchEvent(p1)
                    }
                    return true
                }
            })
        }*/

        init(holder.binding,position,model.url.trim())

    }

    private fun init(binding: ParentLayoutBinding, position: Int, url: String) {
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
//                exoPlayer.play()
            exoPlayer.pause()
        }else{
            exoPlayer.playWhenReady = false
            exoPlayer.pause()
        }

        videoPreparedListner.onVideoPrepared(ExoplayerItem(exoPlayer,absoluteAdapterPosition))
    }
  /*  override fun onViewRecycled(holder: VideoVideoHolder) {
        val position: Int = holder.adapterPosition
        if (holder.binding.playerview.player != null) {
            holder.binding.playerview.player?.release()
        }
        super.onViewRecycled(holder)
    }*/

    interface OnVideoPreparedListner {
        fun onVideoPrepared(exoPlayerItem: ExoplayerItem)

    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        releasePlayer()
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