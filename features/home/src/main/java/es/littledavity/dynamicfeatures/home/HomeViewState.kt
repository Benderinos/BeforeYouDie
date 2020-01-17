package es.littledavity.dynamicfeatures.home

import es.littledavity.commons.ui.base.BaseViewState

/**
 * Different states for [HomeFragment].
 *
 * @see BaseViewState
 */
sealed class HomeViewState : BaseViewState {

    /**
     * Full screen mode.
     */
    object FullScreen : HomeViewState()

    /**
     * Navigation screen mode with bottom bar.
     */
    object NavigationScreen : HomeViewState()

    /**
     * Error on loading logging
     */
    object Error : HomeViewState()

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

    /**
     * Check if current view state is [NavigationScreen].
     *
     * @return True if is navigation screen state, otherwise false.
     */
    fun isNavigationScreen() = this is NavigationScreen
}