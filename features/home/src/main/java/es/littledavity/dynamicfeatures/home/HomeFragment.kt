package es.littledavity.dynamicfeatures.home

import android.os.Bundle
import android.view.View
import es.littledavity.beforeyoudie.BeforeYouDieApp
import es.littledavity.commons.ui.base.BaseFragment
import es.littledavity.core.utils.ThemeUtils
import es.littledavity.dynamicfeatures.home.databinding.FragmentHomeBinding
import es.littledavity.dynamicfeatures.home.di.DaggerHomeComponent
import es.littledavity.dynamicfeatures.home.di.HomeModule
import javax.inject.Inject

/**
 * Login launch firebase auth ui for home
 *
 * @see BaseFragment
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    layoutId = R.layout.fragment_home
) {

    @Inject
    lateinit var themeUtils: ThemeUtils

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
        setupToolbar()
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerHomeComponent
            .builder()
            .coreComponent(BeforeYouDieApp.coreComponent(requireContext()))
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    /**
     * Remove observers
     */
    override fun onClear() {
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    // ============================================================================================
    //  Private setups methods
    // ============================================================================================

    /**
     * Configure app custom support action bar.
     */
    private fun setupToolbar() {
        setHasOptionsMenu(true)
        requireCompatActivity().setSupportActionBar(viewBinding.toolbar)
    }

}