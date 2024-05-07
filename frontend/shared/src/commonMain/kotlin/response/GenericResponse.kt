package response

interface GenericResponse<T> {
    val success: Boolean
    val body: Any
    val message: String
    val request: T
}