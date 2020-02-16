package uk.co.jatra.storearchitecture.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.jatra.storearchitecture.data.User

interface Api {
    @GET("api")
    fun getNames(@Query("gender") gender: String, @Query("amount") amount: Int = 25): Single<List<User>>
}
