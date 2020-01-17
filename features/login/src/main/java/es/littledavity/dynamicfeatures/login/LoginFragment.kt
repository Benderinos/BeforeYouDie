package es.littledavity.dynamicfeatures.login

import android.os.Bundle
import android.view.View
import es.littledavity.beforeyoudie.BeforeYouDieApp
import es.littledavity.commons.ui.base.BaseFragment
import es.littledavity.commons.ui.extensions.observe
import es.littledavity.dynamicfeatures.login.databinding.FragmentLoginBinding
import es.littledavity.dynamicfeatures.login.di.DaggerLoginComponent
import es.littledavity.dynamicfeatures.login.di.LoginModule

/**
 * Login launch firebase auth ui for login
 *
 * @see BaseFragment
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    layoutId = R.layout.fragment_login
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
        DaggerLoginComponent
            .builder()
            .coreComponent(BeforeYouDieApp.coreComponent(requireContext()))
            .loginModule(LoginModule(this))
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
     * Observer view state change on [LoginViewState].
     *
     * @param viewState State of splash fragment.
     */
    private fun onViewStateChange(viewState: LoginViewState) {
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