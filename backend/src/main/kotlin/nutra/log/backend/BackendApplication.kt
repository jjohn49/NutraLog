package nutra.log.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class BackendApplication {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }
}

fun main(args: Array<String>) {
    runApplication<BackendApplication>(*args)
}
