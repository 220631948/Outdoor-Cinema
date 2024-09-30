package za.ac.cput.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PromoDTO {
    private Long id;
    private String code;
    private int discountPercentage;
    private Date startDate;
    private Date endDate;
}