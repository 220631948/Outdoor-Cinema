package za.ac.cput.factory;

import za.ac.cput.domain.Promo;
import za.ac.cput.utils.HelperUtils;

import java.util.Date;

public class PromoFactory {

    public static Promo createPromo(String code, int discountPercentage, Date startDate, Date endDate) {
        if (startDate == null || endDate == null || startDate.after(endDate)) {
            throw new IllegalArgumentException("Invalid start or end date");
        }

        return new Promo.Builder()
                .setCode(code)
                .setDiscountPercentage(discountPercentage)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();
    }

    public static Promo createPromoWithoutEndDate(String code, int discountPercentage, Date startDate) {
        if (!HelperUtils.isNullOrEmpty(code)) {
            throw new IllegalArgumentException("Invalid promo code");
        }
        if (discountPercentage <= 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 1 and 100");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("Start date is required");
        }

        return new Promo.Builder()
                .setCode(code)
                .setDiscountPercentage(discountPercentage)
                .setStartDate(startDate)
                .build();
    }
}