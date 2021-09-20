package com.estore.core.query;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FetchUserPaymentDetailsQuery {
    String userId;
}
