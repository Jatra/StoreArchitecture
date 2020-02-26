package uk.co.jatra.storearchitecture.ui

import androidx.lifecycle.*
import uk.co.jatra.storearchitecture.repository.UsersRepository
import kotlin.random.Random

//TODO inject (or use a service locator) to get repository

class UserViewModel: ViewModel() {

    private var trigger: MutableLiveData<Boolean> = MutableLiveData(false)

    val data: LiveData<List<String>> = trigger.switchMap { triggerValue ->
        liveData {
            emit(UsersRepository.getUsers(triggerValue).map { it.name })
        }
    }

    fun refresh() {
        trigger.value = Random.nextBoolean()
    }
}

/*
NB docs show
    liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
*/