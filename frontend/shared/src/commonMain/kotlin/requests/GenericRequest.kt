package requests

import io.ktor.client.HttpClient

interface GenericRequest{
    val client: HttpClient
        get() = HttpClient()

    suspend fun makeRequest(): Any
}
