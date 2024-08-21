package code.faizal.androidexpert.favorite.presentation.screen

import android.content.Intent
import code.faizal.androidexpert.favorite.presentation.state.FavoriteViewModel
import code.faizal.androidexpert.favorite.presentation.state.ViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.presentation.adapter.MovieAdapter
import code.faizal.androidexpert.di.FavoriteModuleDependencies
import code.faizal.androidexpert.favorite.databinding.ActivityFavoriteBinding
import code.faizal.androidexpert.favorite.di.DaggerFavoriteComponent
import code.faizal.androidexpert.presentation.screen.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteActivity : AppCompatActivity() {

    private var _binding : ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter : MovieAdapter

    // DAGGER INJECTION
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private  val  favoriteViewModel : FavoriteViewModel by viewModels{
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(EntryPointAccessors.fromApplication(applicationContext,
                FavoriteModuleDependencies::class.java))
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        showMyFavorite()
    }


    private fun setRecycler(data : List<Movie>){
        movieAdapter = MovieAdapter(object : MovieAdapter.OnClick{
            override fun onDetail(movie: Movie) {
                startActivity(Intent(this@FavoriteActivity, DetailActivity::class.java).also{
                    it.putExtra(EXTRA_DATA,movie)
                })
            }
        })

        movieAdapter.submitData(data)

        binding.rvMovie.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }

    private fun showMyFavorite(){
        favoriteViewModel.getAllFavorite.observe(this){
            setRecycler(it)
        }
    }

    companion object{
        const val EXTRA_DATA = "movie"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}