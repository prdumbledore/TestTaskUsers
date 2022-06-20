package com.eriksargsyan.testtaskusers

import com.eriksargsyan.testtaskusers.model.data.database.UserDB
import com.eriksargsyan.testtaskusers.model.data.domain.EyeColor
import com.eriksargsyan.testtaskusers.model.data.domain.Fruit
import com.eriksargsyan.testtaskusers.model.data.domain.Gender
import com.eriksargsyan.testtaskusers.model.data.domain.User
import com.eriksargsyan.testtaskusers.model.data.network.FriendsNet
import com.eriksargsyan.testtaskusers.model.data.network.UserNet
import com.eriksargsyan.testtaskusers.model.database.UserDao
import com.eriksargsyan.testtaskusers.model.network.SettingsStorage
import com.eriksargsyan.testtaskusers.model.network.UserAPI
import com.eriksargsyan.testtaskusers.model.repository.UserRepositoryImpl
import com.eriksargsyan.testtaskusers.util.UserDatabaseMapper
import com.eriksargsyan.testtaskusers.util.UserNetworkMapper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock


class UserRepositoryTest {

    lateinit var userRepository: UserRepositoryImpl

    private val token = BuildConfig.TOKEN

    private val userAPI = mock<UserAPI>()

    private val userDao = mock<UserDao>()

    private val settingsStorage = mock<SettingsStorage>()

    private val userDatabaseMapper = UserDatabaseMapper()

    private val userNetworkMapper = UserNetworkMapper()

    private val mDispatcher = Dispatchers.Unconfined


    @Before
    fun start() {
        userRepository = UserRepositoryImpl(
            userAPI,
            userDao,
            settingsStorage,
            userDatabaseMapper,
            userNetworkMapper,
            mDispatcher
        )
    }

    @Test
    fun cacheAndGetTest() = runBlocking  {
        Mockito.`when`(settingsStorage.getToken()).thenReturn(token)
        Mockito.`when`(userAPI.getUsers(token = token)).thenReturn(listOf(user0Net))
        Mockito.`when`(userDao.insertUser(user0DB)).thenReturn(null)
        assertEquals(userRepository.cacheAndGet(), listOf(user0Domain))

    }

    @Test
    fun getUsersTest() = runBlocking {
        Mockito.`when`(userDao.getAllUsers()).thenReturn(listOf(user0DB))

        assertEquals(userRepository.getUsers(), listOf(user0Domain))
    }

    @Test
    fun getUserByIdTest() = runBlocking {
        Mockito.`when`(userDao.getById(0)).thenReturn(user0DB)

        assertEquals(userRepository.getUser(0), user0Domain)
    }

    companion object {
        val user0Net = UserNet(
            id = 0,
            isActive = true,
            age = 37,
            eyeColor = "blue",
            name = "Aisha Velasquez",
            gender = "female",
            company = "BUGSALL",
            email = "aishavelasquez@bugsall.com",
            phone = "+1 (906) 447-2711",
            address = "176 Tehama Street, Durham, Connecticut, 3324",
            about = "Eu Lorem commodo nisi exercitation dolore. Eiusmod officia mollit proident labore ea" +
                    " nostrud elit esse sit commodo. Magna sunt nostrud magna irure magna exercitation " +
                    "ipsum ullamco irure nostrud.\r\n",
            registered = "2016-02-14T09:26:27 -03:00",
            latitude = -20.150163,
            longitude = -2.513911,
            favoriteFruit = "banana",
            friends = listOf(FriendsNet(id = 7), FriendsNet(id = 1))
        )
        val user0DB = UserDB(
            id = 0,
            isActive = true,
            age = 37,
            eyeColor = EyeColor.COLOR_BLUE,
            name = "Aisha Velasquez",
            gender = Gender.FEMALE,
            company = "BUGSALL",
            email = "aishavelasquez@bugsall.com",
            phone = "+1 (906) 447-2711",
            address = "176 Tehama Street, Durham, Connecticut, 3324",
            about = "Eu Lorem commodo nisi exercitation dolore. Eiusmod officia mollit proident labore ea" +
                    " nostrud elit esse sit commodo. Magna sunt nostrud magna irure magna exercitation " +
                    "ipsum ullamco irure nostrud.\r\n",
            registered = "2016-02-14T09:26:27 -03:00",
            latitude = -20.150163,
            longitude = -2.513911,
            favoriteFruit = Fruit.BANANA,
            friendIds = listOf(7, 1)
        )

        val user0Domain = User(
            id = 0,
            isActive = true,
            age = 37,
            eyeColor = EyeColor.COLOR_BLUE,
            name = "Aisha Velasquez",
            gender = Gender.FEMALE,
            company = "BUGSALL",
            email = "aishavelasquez@bugsall.com",
            phone = "+1 (906) 447-2711",
            address = "176 Tehama Street, Durham, Connecticut, 3324",
            about = "Eu Lorem commodo nisi exercitation dolore. Eiusmod officia mollit proident labore ea" +
                    " nostrud elit esse sit commodo. Magna sunt nostrud magna irure magna exercitation " +
                    "ipsum ullamco irure nostrud.\r\n",
            registered = "2016-02-14T09:26:27 -03:00",
            latitude = -20.150163,
            longitude = -2.513911,
            favoriteFruit = Fruit.BANANA,
            friends = listOf(7, 1)
        )
    }
}