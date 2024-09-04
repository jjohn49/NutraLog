package Database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import models.Day

@Dao
interface DayDao {
    @Insert
    suspend fun insert(item: Day)

    @Query("SELECT count(*) FROM Day")
    suspend fun count(): Int

    @Query("SELECT * FROM Day")
    fun getAllAsFlow(): Flow<List<Day>>
}