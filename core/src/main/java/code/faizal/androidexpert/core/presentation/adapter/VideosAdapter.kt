package code.faizal.androidexpert.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import code.faizal.androidexpert.core.databinding.ListVideosBinding
import code.faizal.androidexpert.core.domain.model.Videos
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class VideosAdapter(
    val data : List<Videos>
) : RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

    inner class VideosViewHolder(val binding : ListVideosBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
       return VideosViewHolder(ListVideosBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int= data.size

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.binding.apply {
            val videos = data[position]
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = videos.key
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }
    }
}