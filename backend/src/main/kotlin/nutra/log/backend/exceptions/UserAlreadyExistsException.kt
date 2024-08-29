package nutra.log.backend.exceptions

import nutra.log.backend.models.Day
import nutra.log.backend.models.User

class UserAlreadyExistsException(
    message: String = "User already exists."
): Exception(message)

class DayDoesNotExistForUserException(
    val day: Day,
    val user: User,
    override val message: String? = "Day: ${day.id} Does not exist for ${user.id}"
): Exception()

