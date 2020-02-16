package uk.co.jatra.storearchitecture.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import uk.co.jatra.storearchitecture.data.User
import uk.co.jatra.storearchitecture.repository.UsersRepository
import kotlin.random.Random

class UserViewModel: ViewModel() {
    var male : Boolean = true
    val names: MutableLiveData<List<String>> = MutableLiveData()

    private fun updateNames(userList: List<User>) {
        names.value = userList.map { it.name }
    }

    fun refresh() {
        male = Random.nextBoolean()
        //Use injection (or a service locator) for less coupling.
        UsersRepository.getUsers(male)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::updateNames)
    }
}