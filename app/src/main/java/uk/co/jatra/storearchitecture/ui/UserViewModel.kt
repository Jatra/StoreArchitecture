package uk.co.jatra.storearchitecture.ui

import androidx.lifecycle.*
import uk.co.jatra.storearchitecture.repository.UsersRepository
import kotlin.random.Random

//TODO inject (or use a service locator) to get repository

class UserViewModel: ViewModel() {
    private var male: MutableLiveData<Boolean> = MutableLiveData(false)

    val names: LiveData<List<String>> = male.switchMap { male ->
        liveData {
            emit(UsersRepository.getUsers(male).map { it.name })
        }
    }

    fun refresh() {
        male.value = Random.nextBoolean()
    }
}

/*
NB docs show
    liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
*/