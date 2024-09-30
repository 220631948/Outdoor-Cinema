package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItem extends JpaRepository<FoodItem, Long> {
    // Implement food item repository methods here
}