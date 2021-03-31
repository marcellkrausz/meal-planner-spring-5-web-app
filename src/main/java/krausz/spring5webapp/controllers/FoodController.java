package krausz.spring5webapp.controllers;

import krausz.spring5webapp.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {

    private FoodRepository foodRepository;

    @Autowired
    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @RequestMapping("/foods")
    public String getFoods(Model model) {
        model.addAttribute("foods",foodRepository.findAll());
        return "foods";
    }
}
