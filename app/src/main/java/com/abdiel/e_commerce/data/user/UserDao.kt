package com.abdiel.e_commerce.data.user

import androidx.room.Dao
import androidx.room.Query
import com.crocodic.core.data.CoreDao
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : CoreDao<User> {
    @Query("SELECT EXISTS(SELECT * FROM User WHERE id = 1)")
    suspend fun isLogin(): Boolean

    @Query("UPDATE User SET photo = :photo WHERE id = 1")
    suspend fun updatePhoto(photo: String)

    @Query("SELECT * FROM User WHERE id = 1")
    fun userLogin(): Flow<User>

    @Query("SELECT * FROM User WHERE id != 1")
    suspend fun getFriends(): List<User>

    @Query("SELECT * FROM User WHERE userId = :userId LIMIT 1")
    suspend fun getFriend(userId: Int): User?

    @Query("DELETE FROM User WHERE id != 1")
    suspend fun deleteNonLogin()

    @Query("UPDATE User SET likeByYou = :liked WHERE userId = :userId")
    suspend fun like(userId: Int, liked: Boolean)

    @Query("SELECT likeByYou FROM User WHERE userId = :userId")
    suspend fun liked(userId: Int): Boolean?
}