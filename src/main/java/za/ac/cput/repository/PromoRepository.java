package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Promo;

import java.util.List;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
    // jpa repository methods
    List<Promo> findByPromoCode(String promoCode);
}