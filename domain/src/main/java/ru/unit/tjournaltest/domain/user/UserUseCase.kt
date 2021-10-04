package ru.unit.tjournaltest.domain.user

import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import javax.inject.Inject

interface UserUseCase {
    suspend fun login(login: String, password: String): UserPOJO?
    fun logout()
    fun isAuthorized(): Boolean
    suspend fun getUserMe(): UserPOJO
    suspend fun clearUserMe()
}

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) : UserUseCase {
    override suspend fun login(login: String, password: String): UserPOJO? {
        val apiResult = userService.login(login, password)
        return if (isAuthorized()) {
            userRepository.setUserMe(apiResult)
            apiResult
        } else null
    }

    override fun logout() {
        userRepository.setXDeviceToken("")
    }

    override fun isAuthorized(): Boolean = !userRepository.getXDeviceToken().isNullOrEmpty()

    override suspend fun getUserMe(): UserPOJO {
        val cacheResult = userRepository.getUserMe()
        if (cacheResult != null) {
            return cacheResult
        }

        val apiResult = userService.getUserMe()
        userRepository.setUserMe(apiResult)

        return apiResult
    }

    override suspend fun clearUserMe() {
        userRepository.clearUserMe()
    }
}
