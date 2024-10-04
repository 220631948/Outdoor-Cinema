package za.ac.cput.factory;

import za.ac.cput.domain.FoodItem;
import za.ac.cput.utils.HelperUtils;

public class FoodItemFactory {

    public static FoodItem createFoodItem(String name, double price, String description, int quantity) {
        if (HelperUtils.isNullOrEmpty(name)
                ||
                HelperUtils.isNullOrEmpty(price)
                || price <= 0
                || HelperUtils.isNullOrEmpty(description)
                || quantity <= 0) {
            return null;
        }

        return new FoodItem.Builder()
                .setName(name)
                .setPrice(price)
                .setDescription(description)
                .setQuantity(quantity)
                .build();
    }

}