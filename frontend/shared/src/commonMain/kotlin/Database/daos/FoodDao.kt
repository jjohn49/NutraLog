package Database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import models.Food


@Dao
interface FoodDao {
    @Insert
    suspend fun insert(item: Food)

    @Query("SELECT count(*) FROM Food")
    suspend fun count(): Int

    @Query("SELECT * FROM Food")
    fun getAllAsFlow(): Flow<List<Food>>

    @Query("SELECT COUNT() FROM Food WHERE id = :id")
    suspend fun numOfFoodsWithId(id: String): Int

    @Query("DELETE FROM Food")
    suspend fun deleteFuckingEverything()
}