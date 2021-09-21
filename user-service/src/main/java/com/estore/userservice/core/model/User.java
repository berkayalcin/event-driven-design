package com.estore.userservice.core.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String userId;
    private PaymentDetail paymentDetail;
}
