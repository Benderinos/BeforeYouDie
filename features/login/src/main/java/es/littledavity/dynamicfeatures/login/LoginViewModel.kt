package es.littledavity.dynamicfeatures.login

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.littledavity.core.network.repositories.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [SplashFragment].
 *
 * @see ViewModel
 */
@ExperimentalCoroutinesApi
class LoginViewModel @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableLiveData<LoginViewState>()
    val state: LiveData<LoginViewState>
        get() = _state

    init {
        _state.postValue(LoginViewState.Loading)
    }

}