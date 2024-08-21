package code.faizal.androidexpert.core.presentation.adapter


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import code.faizal.androidexpert.core.databinding.ListMovieBinding
import code.faizal.androidexpert.core.domain.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private val listener : OnClick):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }


        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id.hashCode() == newItem.id.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this,diffUtil)


    fun submitData(data : List<Movie>) = differ.submitList(data)


    inner class MovieViewHolder(val binding : ListMovieBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ListMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
            val item = differ.currentList[position]
            tvTittle.text = item.originalTitle
            tvDesc.text = item.overview
            Glide.with(root).load("https://image.tmdb.org/t/p/w500${item.posterPath}").into(movieImage)
            cardM.setOnClickListener {
                listener.onDetail(item)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    interface OnClick{
        fun onDetail(movie : Movie)
    }


}

