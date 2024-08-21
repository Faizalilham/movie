package code.faizal.androidexpert.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import code.faizal.androidexpert.core.databinding.ListGenreBinding
import code.faizal.androidexpert.core.domain.model.Genre

class GenreAdapter(val data : List<Genre>, val onClick: (Int)-> Unit) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {


    inner class GenreViewHolder(val binding : ListGenreBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
       return GenreViewHolder(ListGenreBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.apply {
            genreName.text = data[position].name
            card.setOnClickListener {
                onClick(data[position].id)
            }
        }
    }
}