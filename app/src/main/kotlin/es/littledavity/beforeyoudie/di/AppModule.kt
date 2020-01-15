package es.littledavity.beforeyoudie.di

import android.content.Context
import dagger.Module
import dagger.Provides
import es.littledavity.beforeyoudie.BeforeYouDieApp

/**
 * Class that contributes to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
class AppModule {

    /**
     * Create a provider method binding for [Context].
     *
     * @param application Sample Application.
     * @return Instance of context.
     * @see Provides
     */
    @Provides
    fun provideContext(application: BeforeYouDieApp): Context = application.applicationContext
}