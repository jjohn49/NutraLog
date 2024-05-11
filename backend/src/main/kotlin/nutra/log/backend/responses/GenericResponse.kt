package nutra.log.backend.responses

interface GenericResponse<T>{
    val success: Boolean
    val body: Any
    val message: String
    val request: T
}