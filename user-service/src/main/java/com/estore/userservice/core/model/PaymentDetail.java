package com.estore.userservice.core.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaymentDetail {
    private String name;
    private String cardNumber;
    private int validUntilMonth;
    private int validUntilYear;
    private String cvv;
}
