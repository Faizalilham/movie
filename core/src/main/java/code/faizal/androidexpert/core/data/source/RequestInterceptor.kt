package code.faizal.androidexpert.core.data.source


import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class  RequestInterceptor : Interceptor {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjZDQ1ZmM0YTViYTcwYmNhNThkZmY1MGU5Mzg4NGQ0ZiIsIm5iZiI6MTcyNDA3Mjg2OC40Mjc0OCwic3ViIjoiNjMxMWEwYjQ5NDA4ZWMwMDgzZTBmZWQ3Iiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.YfGhYfy24LeuCWMHqz2TJYYQ-2E47_951WnGmVvlERg"
        val originalRequest = chain.request()
        val newRequest: Request = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        loggingInterceptor.intercept(chain)

        return chain.proceed(newRequest)
    }
}