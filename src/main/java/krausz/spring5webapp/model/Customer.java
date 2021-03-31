package krausz.spring5webapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    private String gender;
    private double weight;
    private double height;
    private double bmi;
    private String bmiType;
    @ManyToMany(mappedBy = "customers")
    private Set<Food> recommendedFood;

    public Customer(String name, int age, boolean isMale, double weight, double height) {
        this.name = name;
        this.age = age;
        this.gender = isMale ? "Male" : "Female";
        this.weight = weight;
        this.height = height;
        this.recommendedFood = new HashSet<>();
        calculateBMI();
        calculateBmiType();
    }

    public Customer() {
    }

    private void calculateBMI() {
        double heightInMeters = height / 100;
        double heightSquare = Math.pow(heightInMeters, 2);
        this.bmi = weight / heightSquare;
    }

    private void calculateBmiType() {
        if(this.bmi <= 18.4) {
            this.bmiType = "Underweight";
        } else if (this.bmi >= 18.5 && this.bmi <= 24.9) {
            this.bmiType = "Normal weight";
        } else if (this.bmi >= 25 && this.bmi <= 29.9) {
            this.bmiType = "Overweight";
        } else {
            this.bmiType = "Obese";
        }
    }

    public void addAllFood(List<Food> foods) {
      for (Food food : foods) {
          recommendedFood.add(food);
          food.getCustomers().add(this);
      }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", bmi=" + bmi +
                ", bmiType='" + bmiType + '\'' +
                ", recommendedFood=" + recommendedFood +
                '}';
    }
}
