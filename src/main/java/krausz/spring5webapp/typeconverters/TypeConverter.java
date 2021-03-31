package krausz.spring5webapp.typeconverters;

import krausz.spring5webapp.model.FoodType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<FoodType, String> {
    @Override
    public String convertToDatabaseColumn(FoodType foodType) {
        if (foodType == null) {
            return null;
        }
        return foodType.getType();
    }

    @Override
    public FoodType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }
        return Stream.of(FoodType.values())
                .filter(a -> a.getType().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
