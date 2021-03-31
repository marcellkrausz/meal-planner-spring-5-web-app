package krausz.spring5webapp.bootstrap;

import krausz.spring5webapp.model.Customer;
import krausz.spring5webapp.model.Food;
import krausz.spring5webapp.model.FoodType;
import krausz.spring5webapp.repositories.CustomerRepository;
import krausz.spring5webapp.repositories.FoodRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CustomerRepository customerRepository;
    private FoodRepository foodRepository;

    public DevBootstrap(CustomerRepository customerRepository, FoodRepository foodRepository) {
        this.customerRepository = customerRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Food chickenBreast = new Food("Chicken Breast", FoodType.PROTEINS);
        Food roostedBeef = new Food("Roosted Beef", FoodType.PROTEINS);
        Food oliveOil = new Food("Olive oil", FoodType.FATS);
        Food potato = new Food("Potato", FoodType.CARBOHYDRATES);
        Food salad = new Food("Salad", FoodType.CARBOHYDRATES);

        Customer marcell = new Customer("Marcell",27,true,90.0,173.0);
        marcell.addAllFood(Arrays.asList(chickenBreast,oliveOil,salad));
        customerRepository.save(marcell);

        Customer zsolt = new Customer("Zsolt",24,true,75.0,180);
        zsolt.addAllFood(Arrays.asList(roostedBeef,potato,salad));
        customerRepository.save(zsolt);

        foodRepository.saveAll(Arrays.asList(chickenBreast,roostedBeef,oliveOil,potato,salad));
    }
}
