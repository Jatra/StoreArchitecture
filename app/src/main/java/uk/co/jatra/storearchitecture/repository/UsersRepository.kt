package uk.co.jatra.storearchitecture.repository

import uk.co.jatra.storearchitecture.api.ApiAdapter
import uk.co.jatra.storearchitecture.data.User

object UsersRepository {
    //Use injection or service locator for less coupling.
    private val webservice = ApiAdapter.adapter

    suspend fun getUsers(male: Boolean): List<User> {
        //add some caching to avoid getting new result EVERY time.
        return webservice.getNames(if (male) "male" else "female")
    }
}


//Better would be to wrap the return value, so that Loading and Error can also be returned.
sealed class UsersResult {
    object Loading: UsersResult()
    class Error(val reason: Exception): UsersResult()
    class Data(val names: List<User>): UsersResult()
}