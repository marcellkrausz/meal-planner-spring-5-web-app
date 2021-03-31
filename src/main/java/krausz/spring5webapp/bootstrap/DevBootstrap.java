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
import java.util.Set;

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
        marcell.getRecommendedFood().addAll(Arrays.asList(chickenBreast,oliveOil,salad));
        chickenBreast.getCustomers().add(marcell);
        oliveOil.getCustomers().add(marcell);
        customerRepository.save(marcell);

        Customer zsolt = new Customer("Zsolt",24,true,75.0,180);
        zsolt.getRecommendedFood().addAll(Arrays.asList(roostedBeef,potato,salad));
        roostedBeef.getCustomers().add(zsolt);
        potato.getCustomers().add(zsolt);
        customerRepository.save(zsolt);

        salad.getCustomers().addAll(Set.of(marcell,zsolt));
        foodRepository.saveAll(Arrays.asList(chickenBreast,roostedBeef,oliveOil,potato,salad));
    }
}
