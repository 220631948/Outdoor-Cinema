package za.ac.cput.domain;

import java.util.Date;
import java.util.Objects;

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

    protected Promo() {
    }

    private Promo(Builder builder) {
        this.id = builder.id;
        this.code = builder.code;
        this.discountPercentage = builder.discountPercentage;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Promo promo = (Promo) o;
        return discountPercentage == promo.discountPercentage && Objects.equals(id, promo.id)
                && Objects.equals(code, promo.code) && Objects.equals(startDate, promo.startDate)
                && Objects.equals(endDate, promo.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, discountPercentage, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Promo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public static class Builder {
        private Long id;
        private String code;
        private int discountPercentage;
        private Date startDate;
        private Date endDate;

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setDiscountPercentage(int discountPercentage) {
            this.discountPercentage = discountPercentage;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Promo build() {
            return new Promo(this);
        }

        public Builder copy(Promo promo) {
            this.id = promo.id;
            this.code = promo.code;
            this.discountPercentage = promo.discountPercentage;
            this.startDate = promo.startDate;
            this.endDate = promo.endDate;
            return this;
        }
    }
}