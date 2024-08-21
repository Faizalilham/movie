package code.faizal.androidexpert.core.utils


import code.faizal.androidexpert.core.data.source.local.entity.MovieEntity
import code.faizal.androidexpert.core.data.source.remote.reponse.MovieResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.detail.MovieDetailResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.genre.GenreResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.review.AuthorDetailsResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.review.ListReviewResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.review.ReviewResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.videos.ListVideoResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.videos.VideosResponse
import code.faizal.androidexpert.core.domain.model.AuthorDetails
import code.faizal.androidexpert.core.domain.model.Genre
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.domain.model.MovieDetail
import code.faizal.androidexpert.core.domain.model.MovieReview
import code.faizal.androidexpert.core.domain.model.Review
import code.faizal.androidexpert.core.domain.model.Videos

object DataMapper {

        fun mapFromResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
            val list = mutableListOf<MovieEntity>()
            input.map { movie ->
                movie.apply {
                    val movieEntity =
                        MovieEntity(
                            newPathPoster,
                            isAdult,
                            movieOverview,
                            releaseDate,
                            movieId,
                            originalTitle,
                            originalLanguage,
                            movieTitle,
                            backdropPath,
                            popularity,
                            voteCount,
                            isVideo,
                            averageRating
                        )
                    list.add(movieEntity)
                }
            }
            return list
        }

    fun mapFromResponseToDomain(input: List<MovieResponse>): List<Movie> {
        val list = mutableListOf<Movie>()
        input.map { movie ->
            movie.apply {
                val movieEntity =
                    Movie(
                        newPathPoster,
                        isAdult,
                        movieOverview,
                        releaseDate,
                        movieId,
                        originalTitle,
                        originalLanguage,
                        movieTitle,
                        backdropPath,
                        popularity,
                        voteCount,
                        isVideo,
                        averageRating,false
                    )
                list.add(movieEntity)
            }
        }
        return list
    }

        fun mapFromEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
            val listMovie = mutableListOf<Movie>()
            input.map { movie ->
                movie.apply {
                    val movieDomain = Movie(
                        newPathPoster,
                        isAdult,
                        movieOverview,
                        releaseDate,
                        movieId,
                        originalTitle,
                        originalLanguage,
                        movieTitle,
                        backdropPath,
                        popularity,
                        voteCount,
                        isVideo,
                        averageRating,
                        isFavorite
                    )

                    listMovie.add(movieDomain)
                }
            }
            return listMovie
        }

        fun mapFromDomainToEntity(input: Movie): MovieEntity {
            return MovieEntity(
                input.posterPath,
                input.isAdult,
                input.overview,
                input.releaseDate,
                input.id,
                input.originalTitle,
                input.originalLanguage,
                input.movieTitle,
                input.backdropPath,
                input.popularity,
                input.voteCount,
                input.isVideo,
                input.averageRating,
                input.isFavorite
            )
        }

    fun mapFromListGenreResponseToDomain(input : List<GenreResponse>):List<Genre>{
        val genres = mutableListOf<Genre>()
        input.map { genre ->
            genres.add(
                Genre(
                    genre.id,
                    genre.name
                )
            )
        }
        return genres
    }

    fun mapFromListReviewResponseToDomain(input : ListReviewResponse):Review{
        return Review(
            input.id,
            input.page,
            mapFromListMovieReviewResponseToDomain(input.results),
            input.totalPages,
            input.totalResults
        )
    }

    private fun mapFromListMovieReviewResponseToDomain(input : List<ReviewResponse>): List<MovieReview>{
        val reviews = mutableListOf<MovieReview>()
        input.map { review ->
            reviews.add(
                MovieReview(
                    review.author,
                    mapFromAuthorDetailResponseToDomain(review.authorDetails),
                    review.content,
                    review.createdAt,
                    review.id,
                    review.updatedAt,
                    review.url
                )
            )
        }
        return reviews
    }

    private fun mapFromAuthorDetailResponseToDomain(input : AuthorDetailsResponse) : AuthorDetails{
        return AuthorDetails(
            input.name,
            input.username,
            input.avatarPath,
            input.rating
        )
    }

    fun mapFromListVideoResponseToDomain(input : List<VideosResponse>): List<Videos>{
        val videos = mutableListOf<Videos>()
        input.map { video ->
            videos.add(
                Videos(
                    video.iso6391,
                    video.iso31661,
                    video.name,
                    video.key,
                    video.site,
                    video.size,
                    video.type,
                    video.official,
                    video.publishedAt,
                    video.videoId
                )
            )
        }
        return videos
    }

    fun mapFromMovieDetailResponseToDomain(input : MovieDetailResponse):MovieDetail{
        return MovieDetail(
            input.adult,
            input.backdropPath,
            input.budget,
            mapFromListGenreResponseToDomain(input.genres),
            input.homepage,
            input.id,
            input.imdbId,
            input.originCountry,
            input.originalLanguage,
            input.originalTitle,
            input.overview,
            input.popularity,
            input.backdropPath,
            input.releaseDate,
            input.revenue ,
            input.runtime ,
            input.status,
            input.tagline ?: "",
            input.title,
            input.video,
            input.voteAverage,
            input.voteCount,

        )
    }
}
