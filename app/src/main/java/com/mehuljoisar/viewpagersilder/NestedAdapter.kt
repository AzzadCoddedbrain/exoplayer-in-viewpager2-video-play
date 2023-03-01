import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehuljoisar.viewpagersilder.VideoAdapter
import com.mehuljoisar.viewpagersilder.databinding.NestedLayoutBinding
import com.mehuljoisar.viewpagersilder.model.Video

class NestedAdapter(var list: ArrayList<String>) : RecyclerView.Adapter<NestedAdapter.VideoHolder>() {

    private lateinit var binding: NestedLayoutBinding



    class VideoHolder(var binding: NestedLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: String) {
            binding.textView.text = word

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        binding = NestedLayoutBinding.inflate(LayoutInflater.from(binding.root.context), parent, false)
        return VideoHolder(binding)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(list.get(position))




    }

}