package code.faizal.androidexpert.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import code.faizal.androidexpert.core.databinding.ListReviewBinding
import code.faizal.androidexpert.core.domain.model.MovieReview
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReviewAdapter(
    val data : List<MovieReview>
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    inner class ReviewViewHolder(val binding : ListReviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(ListReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.binding.apply {
            val review = data[position]
            tvAuthor.text = review.author
            if(review.authorDetails.avatarPath == null){
                Glide.with(imgAuthor.context).load("https://res.cloudinary.com/dmhpacb7m/image/upload/v1715219008/villa/k1exgbemm4aqcd5kfo4n.jpg").into(imgAuthor)
            }else{
                Glide.with(imgAuthor.context).load("https://image.tmdb.org/t/p/w500${review.authorDetails.avatarPath}").into(imgAuthor)
            }
            tvContent.text = review.content
            rating.rating = (review.authorDetails.rating?.toFloat()?.div(2)) ?: 0F
            tvDate.text = convertDate(review.createdAt)
            var isTextExpanded = false
            tvNextDescription.setOnClickListener {
                if (isTextExpanded) {
                    tvContent.maxLines = 3
                    tvContent.ellipsize = android.text.TextUtils.TruncateAt.END
                    tvNextDescription.text = "Selengkapnya"
                } else {
                    tvContent.maxLines = Integer.MAX_VALUE
                    tvContent.ellipsize = null
                    tvNextDescription.text = "Lebih Sedikit"
                }
                isTextExpanded = !isTextExpanded
            }
        }
    }

    private fun convertDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("id", "ID"))
        inputFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("dd MMMM yyyy 'pukul' HH:mm", Locale("id", "ID"))

        val date: Date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }
}