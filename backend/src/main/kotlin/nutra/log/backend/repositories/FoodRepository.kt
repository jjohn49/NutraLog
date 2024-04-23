package nutra.log.backend.repositories

import nutra.log.backend.models.Food
import org.springframework.data.mongodb.repository.MongoRepository

interface FoodRepository: MongoRepository<Food, String> {

}