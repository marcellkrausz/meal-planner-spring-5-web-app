package krausz.spring5webapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getBmiType() {
        return bmiType;
    }

    public void setBmiType(String bmiType) {
        this.bmiType = bmiType;
    }

    public Set<Food> getRecommendedFood() {
        return recommendedFood;
    }

    public void setRecommendedFood(Set<Food> recommendedFood) {
        this.recommendedFood = recommendedFood;
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
