package Database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import models.UserKMM

@Dao
interface UserKMMDao {
    @Insert
    suspend fun insert(item: UserKMM)

    @Query("SELECT count(*) FROM UserKMM")
    suspend fun count(): Int

    @Query("SELECT * FROM UserKMM")
    fun getAllAsFlow(): Flow<List<UserKMM>>
}