import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mehuljoisar.viewpagersilder.VideoAdapter
import com.mehuljoisar.viewpagersilder.databinding.NestedLayoutBinding
import com.mehuljoisar.viewpagersilder.model.Video

class NestedAdapter(var list: ArrayList<String>, var context: Context) : RecyclerView.Adapter<NestedAdapter.VideoHolder>() {


    lateinit var adapter: VideoAdapter

    private val videos = ArrayList<Video>()
    private val exoplayerItems= ArrayList<VideoAdapter.ExoplayerItem>()



    class VideoHolder(var binding: NestedLayoutBinding) : RecyclerView.ViewHolder(binding.root) ,
        View.OnAttachStateChangeListener {

        init {
            binding.root.addOnAttachStateChangeListener(this)
        }


        fun bind(word: String) {
            binding.textView.text = word

        }

        override fun onViewAttachedToWindow(view: View) {

        }

        override fun onViewDetachedFromWindow(view: View) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val binding = NestedLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return VideoHolder(binding)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(list.get(position))

        createList()

        inti(holder.binding)



    }

    private fun inti(binding: NestedLayoutBinding) {
        adapter = VideoAdapter(context,videos,object : VideoAdapter.OnVideoPreparedListner{
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
                    Log.e("TAG", "onPageSelected: playWhenReady false "+position )
                }
                val newIndex = exoplayerItems.indexOfFirst { it.position == position }
                if (newIndex != -1){
                    val player = exoplayerItems[newIndex].exoplayer
                    player.playWhenReady = true
                    player.play()
                    Log.e("TAG", "onPageSelected: view pager  play "+position )
                }
            }
        })

    }

    private fun createList() {
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
    }

    override fun onViewDetachedFromWindow(holder: VideoHolder) {
        super.onViewDetachedFromWindow(holder)
        if (exoplayerItems.isNotEmpty()){
            for (i in exoplayerItems){
                val player = i.exoplayer
                player.pause()
                Log.e("TAG", "onViewDetachedFromWindow:  Stop "+holder.position )
                player.clearMediaItems()
            }
        }
    }

    override fun onViewAttachedToWindow(holder: VideoHolder) {
        super.onViewAttachedToWindow(holder)
        val index = exoplayerItems.indexOfFirst { it.position==holder.binding.viewpager2.currentItem }
        if (index != -1){
            val player = exoplayerItems[index].exoplayer
            player.playWhenReady = true
            player.play()
            Log.e("TAG", "onViewAttachedToWindow: -- > play "+holder.position )
        }
    }

}