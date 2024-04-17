package nutra.log.backend.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
@Component
class FDCService {

    @Value("\${fdc-api-key}")
    lateinit var api_key: String



}