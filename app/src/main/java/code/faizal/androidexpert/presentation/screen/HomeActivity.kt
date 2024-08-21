package code.faizal.androidexpert.presentation.screen


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import code.faizal.androidexpert.core.data.Resource
import code.faizal.androidexpert.core.domain.model.Genre
import code.faizal.androidexpert.core.presentation.adapter.GenreAdapter
import code.faizal.androidexpert.databinding.ActivityHomeBinding
import code.faizal.androidexpert.presentation.state.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var _binding : ActivityHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var genreAdapter: GenreAdapter

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataGenre()
        setSupportActionBar(binding.toolbarLayout.toolbar)
        supportActionBar?.title = "Movie Genre"
    }

    private fun showGenre(data : List<Genre>){
        genreAdapter = GenreAdapter(data){ id ->
            startActivity(Intent(this@HomeActivity,MovieActivity::class.java).also{
                it.putExtra(EXTRA_ID,id)
            })
        }

        binding.rvGenre.apply {
            adapter = genreAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }
    }

    private fun showDataGenre(){
        homeViewModel.getAllGenres.observe(this){
            when(it){
                is Resource.Success -> {
                    it.data?.let { genre -> showGenre(genre) }
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

    companion object{
        val EXTRA_ID = "genre_id"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}