package Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DBBuilder(
    private val app: Context
) {
    actual fun build(): AppDatabase {
        return getDatabaseBuilder(app).build()
    }

    fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase> {
        val appContext = ctx.applicationContext
        val dbFile = appContext.getDatabasePath("my_room.db")
        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}