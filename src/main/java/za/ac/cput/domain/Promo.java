package za.ac.cput.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter

@Entity
@Table(name = "promos")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promo_id", nullable = false)
    private Long id;

    @Column(name = "promo_code", nullable = false)
    @NotBlank(message = "Promo code is required")
    @Size(min = 3, max = 25, message = "Promo code must be between 3 and 25 characters")
    private String code;

    @Column(name = "discount_percentage", nullable = false)
    @Size(min = 1, max = 100, message = "Discount percentage must be between 1 and 100")
    @NotBlank(message = "Promo code is required")
    @Positive(message = "Discount percentage must be a positive integer")
    private int discountPercentage;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    @NotBlank(message = "Start date is required")
    @Size(min = 10, max = 10, message = "Start date must be in the format yyyy-MM-dd")
    private java.util.Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date", nullable = false)
    @NotBlank(message = "End date is required")
    @Size(min = 10, max = 10, message = "End date must be in the format yyyy-MM-dd")
    private Date endDate;

}