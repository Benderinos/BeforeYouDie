package es.littledavity.core.di

import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Component
import es.littledavity.core.di.modules.ContextModule
import es.littledavity.core.di.modules.RepositoryModule
import es.littledavity.core.di.modules.UtilsModule
import es.littledavity.core.utils.ThemeUtils
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(modules = [
    ContextModule::class,
    UtilsModule::class,
    RepositoryModule::class
])
interface CoreComponent {

    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context

    /**
     * Provide dependency graph ThemeUtils
     *
     * @return ThemeUtils
     */
    fun themeUtils(): ThemeUtils

    /**
     * Provide dependency graph user database reference
     *
     * @return Context
     */
    fun database(): FirebaseDatabase

    /**
     * Provide dependency graph user database reference
     *
     * @return Context
     */
    fun userDatabase(): DatabaseReference
}