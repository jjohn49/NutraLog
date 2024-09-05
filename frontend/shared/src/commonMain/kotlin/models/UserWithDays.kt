package models

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithDays(
    @Embedded val userKMM: UserKMM,
    @Relation(
        parentColumn = "id",
        entity = Day::class,
        entityColumn = "userId"
    ) val days: List<Day>
)
