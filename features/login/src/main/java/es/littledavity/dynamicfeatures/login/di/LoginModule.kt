package es.littledavity.dynamicfeatures.login.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import dagger.Module
import dagger.Provides
import es.littledavity.core.di.scopes.FeatureScope
import es.littledavity.core.network.repositories.UserRepository
import es.littledavity.dynamicfeatures.login.LoginFragment
import es.littledavity.dynamicfeatures.login.LoginViewModel
import es.littledavity.dynamicfeatures.splash.model.UserMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import es.littledavity.commons.ui.extensions.viewModel

/**
 * Class that contributes to the object graph [LoginComponent].
 *
 * @see Module
 */
@Module
class LoginModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: LoginFragment
) {

    /**
     * Create a provider method binding for [LoginViewModel].
     *
     * @return Instance of view model.
     * @see Provides
     */
    @ExperimentalCoroutinesApi
    @Provides
    @FeatureScope
    fun providesLoginViewModel(
        userRepository: UserRepository
    ) = fragment.viewModel {
        LoginViewModel(userRepository = userRepository)
    }

    /**
     * Create a provider method binding for [UserMapper].
     *
     * @return instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesUserMapper() = UserMapper()
}