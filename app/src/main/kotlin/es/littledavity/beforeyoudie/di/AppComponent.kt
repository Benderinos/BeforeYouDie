package es.littledavity.beforeyoudie.di

import dagger.Component
import es.littledavity.beforeyoudie.BeforeYouDieApp
import es.littledavity.core.di.CoreComponent
import es.littledavity.core.di.scopes.AppScope

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: BeforeYouDieApp)
}