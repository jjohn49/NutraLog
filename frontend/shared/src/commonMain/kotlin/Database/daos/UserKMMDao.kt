package Database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import models.UserKMM
import models.UserWithDays

@Dao
interface UserKMMDao {
    @Insert
    suspend fun insert(item: UserKMM)

    @Update
    suspend fun update(userKMM: UserKMM)

    @Delete
    suspend fun delete(item: UserKMM)

    @Query("SELECT count(*) FROM UserKMM")
    suspend fun count(): Int

    @Query("SELECT * FROM UserKMM")
    suspend fun getUserAndDays(): List<UserWithDays>

    @Query("SELECT COUNT() FROM UserKMM WHERE id = :id")
    suspend fun numOfUsersWithId(id: String): Int

    @Query("DELETE FROM UserKMM")
    suspend fun deleteFuckingEverything()
}