package Database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import models.Day

@Dao
interface DayDao {
    @Insert
    suspend fun insert(item: Day)

    @Delete
    suspend fun delete(item: Day)

    @Update
    suspend fun update(item: Day)

    @Query("SELECT * FROM DAY WHERE date = :date")
    suspend fun getDayForDate(date: String)

    @Query("SELECT count(*) FROM Day")
    suspend fun count(): Int

    @Query("SELECT * FROM Day")
    fun getAllAsFlow(): Flow<List<Day>>

    @Query("SELECT * FROM Day WHERE userId = :userID")
    suspend fun getDaysForUser(userID: String)
}