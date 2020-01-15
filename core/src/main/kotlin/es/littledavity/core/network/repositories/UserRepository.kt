package es.littledavity.core.network.repositories

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import es.littledavity.core.exceptions.UserException
import es.littledavity.core.network.responses.UserResponse
import es.littledavity.core.utils.LOGIN_IN
import es.littledavity.core.utils.LOGIN_OUT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


/**
 * Repository module for handling user data from firebase.
 */
@ExperimentalCoroutinesApi
class UserRepository @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    var mDatabaseUsers: DatabaseReference
) {

    suspend fun storeUser(user: FirebaseUser) : Flow<UserResponse> = callbackFlow {
        val mUser = UserResponse()
        mUser.id = user.uid
        mUser.email = user.email.toString()
        mUser.name = user.displayName.toString()
        mUser.loginStatus = LOGIN_IN
        mUser.avatar = user.photoUrl.toString()
        mDatabaseUsers.child(user.uid).setValue(mUser)
        offer(mUser)
        channel.close()
        awaitClose()
    }

    /**
     * Check if firebase user is logged or not
     *
     */
    suspend fun checkUserLogin() = callbackFlow {
        val callback = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                close(UserException("User not logged"))
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user: UserResponse? = dataSnapshot.getValue(UserResponse::class.java)
                if (user != null) {
                    val mUserData = UserResponse()
                    mUserData.id = user.id
                    mUserData.email = user.email
                    mUserData.name = user.name
                    mUserData.loginStatus = user.loginStatus
                    mUserData.avatar = user.avatar
                    when (mUserData.loginStatus) {
                        LOGIN_IN -> {
                            offer(true)
                        }
                        LOGIN_OUT -> {
                            offer(false)
                        }
                    }
                } else {
                    offer(false)
                }
            }
        }
        FirebaseAuth.getInstance().currentUser?.let {
            mDatabaseUsers.child(it.uid).addListenerForSingleValueEvent(callback)
            awaitClose { mDatabaseUsers.child(it.uid).removeEventListener(callback) }
        } ?: run {
            offer(false)
            awaitClose()
        }
    }
}