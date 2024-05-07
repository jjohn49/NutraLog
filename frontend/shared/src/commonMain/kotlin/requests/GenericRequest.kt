package requests

import io.ktor.client.HttpClient
import response.GenericResponse

interface GenericRequest{
    val client: HttpClient
        get() = HttpClient()

    suspend fun makeRequest(): GenericResponse<*>
}
