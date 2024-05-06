package nutra.log.backend.exceptions

class UserAlreadyExistsException(
    message: String = "User already exists."
): Exception(message) {
}