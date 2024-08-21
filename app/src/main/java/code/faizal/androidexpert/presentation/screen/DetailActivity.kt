package code.faizal.androidexpert.presentation.screen


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import code.faizal.androidexpert.R
import code.faizal.androidexpert.core.data.Resource
import code.faizal.androidexpert.core.domain.model.Genre
import code.faizal.androidexpert.databinding.ActivityDetailBinding
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.domain.model.MovieReview
import code.faizal.androidexpert.core.domain.model.Videos
import code.faizal.androidexpert.core.presentation.adapter.ReviewAdapter
import code.faizal.androidexpert.core.presentation.adapter.VideosAdapter
import code.faizal.androidexpert.presentation.state.DetailViewModel
import code.faizal.androidexpert.presentation.state.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.bumptech.glide.Glide

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel by viewModels<DetailViewModel>()

    private lateinit var reviewsAdapter : ReviewAdapter

    private lateinit var videosAdapter : VideosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }

    private fun initView(){
        binding.imgBack.setOnClickListener { finish() }
        val i = intent.getParcelableExtra<Movie>(MovieActivity.EXTRA_DATA)
        showDetailUI(i)
    }

    private fun showDetailUI(movie : Movie?) {
        binding.apply {
            if(movie != null){
                detailViewModel.movieDetail(movie.id.toInt())
                detailViewModel.getMovie.observe(this@DetailActivity){
                    if(it?.data != null){
                        tvDuration.text = if(it.data!!.runtime != 0) showDuration(it.data!!.runtime!!) else "Movie Duration: -"
                        tvGenre.text =showGenres(it.data!!.genres)
                    }
                }
                val rating = movie.averageRating / 2
                Glide.with(root).load("https://image.tmdb.org/t/p/w500${movie.posterPath}").into(imgMovie)
                binding.tvName.text = movie.originalTitle
                binding.tvOverview.text = movie.overview
                binding.rating.rating = rating
                binding.tvRating.text = movie.averageRating.toString()
                getReview(movie.id.toInt())
                getVideos(movie.id.toInt())
                var isFavorite = movie.isFavorite
                setStatusFavorite(isFavorite)
                binding.fabFavorite.visibility = View.GONE
                binding.fabFavorite.setOnClickListener {
                    isFavorite = !isFavorite
                    detailViewModel.setFavorite(movie,isFavorite)
                    setStatusFavorite(isFavorite)
                    if(isFavorite) Toast.makeText(this@DetailActivity, "Success add to favorite", Toast.LENGTH_SHORT).show() else Toast.makeText(this@DetailActivity, "Success remove to favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showGenres(genres: List<Genre>): String {
        return genres.joinToString(separator = ", ") { it.name }
    }

    private fun showDuration(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime % 60
        return if (hours > 0) {
            "Movie duration : ${hours}h ${minutes}m"
        } else {
            "Movie duration : ${minutes}m"
        }
    }

    private fun <T> observeData(
        liveData: LiveData<Resource<T>>,
        onSuccess: (T) -> Unit
    ) {
        liveData.observe(this) { resource ->
            resource?.let {
                when (it) {
                    is Resource.Success -> {
                        it.data?.let(onSuccess)
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
    }

    private fun getReview(movieId: Int) {
        detailViewModel.getReviews(movieId)
        observeData(detailViewModel.getAllReview) { reviewData ->
            showReviews(reviewData.results)
        }
    }

    private fun getVideos(movieId: Int) {
        detailViewModel.getVideos(movieId)
        observeData(detailViewModel.getAllVideo) { videoData ->
            showVideos(videoData)
        }
    }

    private fun showReviews(data : List<MovieReview>){
        reviewsAdapter = ReviewAdapter(data)
        binding.rvReviews.apply {
            adapter = reviewsAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity,LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun showVideos(data : List<Videos>){
        videosAdapter = VideosAdapter(data)
        binding.rvVideos.apply {
            adapter = videosAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity,LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_filled))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_outlined))
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}