package krausz.spring5webapp.repositories;

import krausz.spring5webapp.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
