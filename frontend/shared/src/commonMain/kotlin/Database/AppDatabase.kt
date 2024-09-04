package Database

import Database.daos.DayDao
import Database.daos.FoodDao
import Database.daos.UserKMMDao
import androidx.room.ConstructedBy
import androidx.room.Database

import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import models.UserKMM
import models.Day
import models.Food

@Database(entities = [UserKMM::class, Day::class, Food::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserKMMDao

    abstract fun getDayDao(): DayDao

    abstract fun getFoodDao(): FoodDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>



