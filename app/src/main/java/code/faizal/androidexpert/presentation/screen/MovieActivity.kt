package code.faizal.androidexpert.presentation.screen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import code.faizal.androidexpert.R
import code.faizal.androidexpert.core.data.Resource
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.presentation.adapter.MovieAdapter
import code.faizal.androidexpert.databinding.ActivityMovieBinding
import code.faizal.androidexpert.presentation.state.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    private var _binding : ActivityMovieBinding? = null

    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter

    private val homeViewModel by viewModels<HomeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val genreId = intent.getIntExtra(HomeActivity.EXTRA_ID,0)
        if(genreId != 0) showDataMovie(genreId) else Toast.makeText(this, "Id 0", Toast.LENGTH_SHORT)
            .show()
        goesToFavorite()

        setSupportActionBar(binding.toolbarLayout.toolbar)
        supportActionBar?.title = "Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setRecyclerview(data : List<Movie>){
        movieAdapter = MovieAdapter(object : MovieAdapter.OnClick{
            override fun onDetail(movie: Movie) {
                startActivity(Intent(this@MovieActivity, DetailActivity::class.java).also {
                    it.putExtra(EXTRA_DATA,movie)
                })
            }
        })

        movieAdapter.submitData(data)
        movieAdapter.notifyDataSetChanged()
        binding.rvMovie.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MovieActivity)
        }
    }


    private fun goesToFavorite(){
        binding.fabFavorite.hide()
        binding.fabFavorite.setOnClickListener {
            val uri = Uri.parse("androidexpert://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

    }

    private fun showDataMovie(genreId : Int){
        homeViewModel.getAllMovie(genreId)
        homeViewModel.getAllMovie.observe(this){
            when(it){
                is Resource.Success -> {
                    it.data?.let {
                        movie -> setRecyclerview(movie)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val EXTRA_DATA = "movie"
    }

}