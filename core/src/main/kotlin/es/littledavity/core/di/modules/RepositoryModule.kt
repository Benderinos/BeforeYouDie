package es.littledavity.core.di.modules

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import es.littledavity.core.di.scopes.AppScope
import es.littledavity.core.network.responses.UserResponse
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
abstract class RepositoryModule {

    /**
     * Create a provider method for [FirebaseDatabase].
     *
     * @return Instance of theme utils.
     * @see Binds
     */
    @AppScope
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.setPersistenceEnabled(true) // enable offline conection
        return firebaseDatabase
    }

    /**
     * Create a provider method for user [DatabaseReference].
     *
     * @return Instance of theme utils.
     * @see Binds
     */
    @AppScope
    @Provides
    fun getUserDatabaseReference(firebaseDatabase: FirebaseDatabase, user: UserResponse): DatabaseReference {
        val databaseReference = firebaseDatabase.getReference("postsByUser").child(user.id!!)
        databaseReference.keepSynced(true) // keep data synced
        return databaseReference
    }
}