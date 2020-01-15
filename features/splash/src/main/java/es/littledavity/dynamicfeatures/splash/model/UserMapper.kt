package es.littledavity.dynamicfeatures.splash.model

import es.littledavity.core.mapper.Mapper
import es.littledavity.core.network.responses.BaseResponse
import es.littledavity.core.network.responses.UserResponse

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
class UserMapper : Mapper<BaseResponse<UserResponse>, User> {

    /**
     * Transform firebase response to [User].
     *
     * @param from Network characters response.
     * @return List of parsed characters items.
     * @throws NoSuchElementException If the response results are empty.
     */
    @Throws(NoSuchElementException::class)
    override suspend fun map(from: BaseResponse<UserResponse>): User {
        val userResponse = from.data.results.first()
        return User(
            id = userResponse.id,
            email = userResponse.email,
            name = userResponse.name,
            internetStatus = userResponse.internetStatus,
            loginStatus = userResponse.loginStatus,
            avatar = userResponse.avatar
        )
    }
}