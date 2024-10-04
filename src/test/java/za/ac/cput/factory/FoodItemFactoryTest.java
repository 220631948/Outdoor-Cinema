package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.FoodItem;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FoodItemFactoryTest {

    @Test
    void testCreateValidFoodItem() {
        String name = "Popcorn";
        double price = 5.99;
        String description = "Delicious buttered popcorn";
        int quantity = 100;

        FoodItem foodItem = FoodItemFactory.createFoodItem(name, price, description, quantity);

        assertNotNull(foodItem);
        assertEquals(name, foodItem.getName());
        assertEquals(price, foodItem.getPrice());
        assertEquals(description, foodItem.getDescription());
        assertEquals(quantity, foodItem.getQuantity());
    }

    @Test
    void testCreateFoodItemWithInvalidPrice() {
        String name = "Popcorn";
        double price = 0;
        String description = "Delicious buttered popcorn";
        int quantity = 100;

        FoodItem foodItem = FoodItemFactory.createFoodItem(name, price, description, quantity);
        assertNull(foodItem);

        price = -5.99;
        foodItem = FoodItemFactory.createFoodItem(name, price, description, quantity);
        assertNull(foodItem);
    }

    @Test
    void testCreateFoodItemWithInvalidQuantity() {
        String name = "Popcorn";
        double price = 17.99;
        String description = "Delicious buttered popcorn";
        int quantity = 0;

        FoodItem foodItem = FoodItemFactory.createFoodItem(name, price, description, quantity);
        assertNull(foodItem);

        quantity = -1;
        foodItem = FoodItemFactory.createFoodItem(name, price, description, quantity);
        assertNull(foodItem);
    }

    @Test
    void testCreateFoodItemWithNonNumericPrice() {
        String name = "Popcorn";
        double price = Double.NaN;
        String description = "Delicious buttered popcorn";
        int quantity = 100;

        FoodItem foodItem = FoodItemFactory.createFoodItem(name, price, description, quantity);
        assertNull(foodItem);
    }

    @Test
    void testCreateFoodItemWithVeryLargeQuantity() {
        String name = "Popcorn";
        double price = 5.99;
        String description = "Delicious buttered popcorn";
        int quantity = Integer.MAX_VALUE;

        FoodItem foodItem = FoodItemFactory.createFoodItem(name, price, description, quantity);

        assertNotNull(foodItem);
        assertEquals(name, foodItem.getName());
        assertEquals(price, foodItem.getPrice());
        assertEquals(description, foodItem.getDescription());
        assertEquals(quantity, foodItem.getQuantity());
    }

    @Test
    void testCreateFoodItemWithVeryLargePrice() {
        String name = "Expensive Item";
        double price = Double.MAX_VALUE;
        String description = "An extremely expensive item";
        int quantity = 1;

        FoodItem foodItem = FoodItemFactory.createFoodItem(name, price, description, quantity);

        assertNotNull(foodItem);
        assertEquals(name, foodItem.getName());
        assertEquals(price, foodItem.getPrice());
        assertEquals(description, foodItem.getDescription());
        assertEquals(quantity, foodItem.getQuantity());
    }

}