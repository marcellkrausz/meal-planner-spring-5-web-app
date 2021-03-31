package krausz.spring5webapp.model;

public enum FoodType {
    CARBOHYDRATES("Carbohydrates"),
    PROTEINS("Proteins"),
    FATS("Fats");

    private final String type;

    FoodType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
