package code.faizal.androidexpert.core.domain.model

data class Review(
    val id: Int,
    val page: Int,
    val results: List<MovieReview>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieReview(
    val author: String,
    val authorDetails: AuthorDetails,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
)

data class AuthorDetails(
    val name: String?,
    val username: String,
    val avatarPath: String?,
    val rating: Int?
)
