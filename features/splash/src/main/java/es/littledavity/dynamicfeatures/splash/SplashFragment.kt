package es.littledavity.dynamicfeatures.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import es.littledavity.beforeyoudie.BeforeYouDieApp
import es.littledavity.commons.ui.base.BaseFragment
import es.littledavity.commons.ui.extensions.observe
import es.littledavity.dynamicfeatures.splash.databinding.FragmentSplashBinding
import es.littledavity.dynamicfeatures.splash.di.DaggerSplashComponent
import es.littledavity.dynamicfeatures.splash.di.SplashModule
import timber.log.Timber

/**
 * Splash check if login and navigate to login screen or home screen
 *
 * @see BaseFragment
 */
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    layoutId = R.layout.fragment_splash
) {

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerSplashComponent
            .builder()
            .coreComponent(BeforeYouDieApp.coreComponent(requireContext()))
            .splashModule(SplashModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewModel.checkUserLogin()
        viewBinding.viewModel = viewModel
    }

    /**
     * Observer view state change on [SplashViewState].
     *
     * @param viewState State of splash fragment.
     */
    private fun onViewStateChange(viewState: SplashViewState) {
        when (viewState) {
            is SplashViewState.Loading ->
                Timber.i("Splash Loading")
            is SplashViewState.Logged ->
                Timber.i("User Logged, navigate to home")
            is SplashViewState.NotLogged ->{
                val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                findNavController().navigate(action)
            }
            is SplashViewState.Error ->
                Timber.i("Error while check user login")
        }
    }

}