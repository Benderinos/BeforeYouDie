package es.littledavity.dynamicfeatures.splash

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.littledavity.core.network.repositories.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [SplashFragment].
 *
 * @see ViewModel
 */
class SplashViewModel @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableLiveData<SplashViewState>()
    val state: LiveData<SplashViewState>
        get() = _state

    init {
        _state
        checkUserLogin()
    }

    private fun checkUserLogin() {
        viewModelScope.launch {
            userRepository.checkUserLogin()
                .onStart { _state.postValue(SplashViewState.Loading) }
                .onEach { ::userLogged }
                .catch { onError(it) }
                .launchIn(viewModelScope)
        }
    }

    private fun userLogged(logged: Boolean) {
        if (logged) {
            _state.postValue(SplashViewState.Logged)
        } else userNotLogged()
    }

    private fun userNotLogged() {
        _state.postValue(SplashViewState.NotLogged)
    }

    private fun onError(t: Throwable) {
        _state.postValue(SplashViewState.Error)
    }
}