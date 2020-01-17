package es.littledavity.dynamicfeatures.login

import es.littledavity.commons.ui.base.BaseViewState

/**
 * Different states for [SplashFragment].
 *
 * @see BaseViewState
 */
sealed class LoginViewState : BaseViewState {

    /**
     * Loading character detail info.
     */
    object Loading : LoginViewState()

    /**
     * Error on loading character detail info.
     */
    object Error : LoginViewState()

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error
}