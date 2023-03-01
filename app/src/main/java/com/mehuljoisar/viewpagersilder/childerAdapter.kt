import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ui.PlayerView
import com.mehuljoisar.viewpagersilder.databinding.ChildLayoutBinding


class childerAdapter(private val list : ArrayList<String>) : RecyclerView.Adapter<childerAdapter.ViewHolder>() {

    private lateinit var binding: ChildLayoutBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ChildLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       /* val ItemsViewModel = mList[position]
        holder.binding(ItemsViewModel)*/
        initPlayer(holder,position);

    }

    private fun initPlayer(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: ChildLayoutBinding) : RecyclerView.ViewHolder(binding.root) ,
        View.OnAttachStateChangeListener  {
        var mExoPlayer: PlayerView = binding.playerview


        fun binding(itemsViewModel: String) {

        }
        init {
            itemView.addOnAttachStateChangeListener(this)
        }

        override fun onViewAttachedToWindow(p0: View) {
            mExoPlayer.player?.play()
        }

        override fun onViewDetachedFromWindow(p0: View) {
            mExoPlayer.player?.pause()
        }
    }
}
