package za.ac.cput.factory;

import za.ac.cput.domain.FoodItem;
import za.ac.cput.utils.HelperUtils;

public class FoodItemFactory {

    public static FoodItem createFoodItem(String name, double price, String description) {
        if (!HelperUtils.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Invalid food item name");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (description != null && description.length() > 255) {
            throw new IllegalArgumentException("Description is too long");
        }

        return new FoodItem.Builder()
                .setName(name)
                .setPrice(price)
                .setDescription(description)
                .build();
    }

    public static FoodItem createFoodItemWithoutDescription(String name, double price) {
        if (!HelperUtils.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Invalid food item name");
        }
        return createFoodItem(name, price, null);
    }

    public static FoodItem createFoodItemWithoutName(double price, String description) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        return createFoodItem(null, price, description);
    }
}