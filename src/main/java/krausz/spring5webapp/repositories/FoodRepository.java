package krausz.spring5webapp.repositories;

import krausz.spring5webapp.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Long> {
}
