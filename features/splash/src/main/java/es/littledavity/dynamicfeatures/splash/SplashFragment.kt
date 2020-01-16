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
        viewBinding.lottieSplash.setOnClickListener {
            viewModel.checkUserLogin()
            viewBinding.lottieSplash.animate()
        }
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
        viewBinding.viewModel = viewModel
    }

    /**
     * Observer view state change on [SplashViewState].
     *
     * @param viewState State of splash fragment.
     */
    private fun onViewStateChange(viewState: SplashViewState) {
        /*when (viewState) {
            is SplashViewState.Loading ->
                //Show Lottie loading
                findNavController().navigate(R.id.action_splashFragment_to_home_fragment)
            is SplashViewState.Logged ->
                findNavController().navigate(R.id.action_splashFragment_to_home_fragment)
            is SplashViewState.NotLogged ->
                findNavController().navigate(R.id.action_splashFragment_to_login_fragment)
            is SplashViewState.Error ->
                findNavController().navigate(R.id.action_splashFragment_to_login_fragment)
        }*/
    }

}