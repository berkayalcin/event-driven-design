package com.estore.userservice.query.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FetchUserPaymentDetailsQuery {
    String userId;
}
