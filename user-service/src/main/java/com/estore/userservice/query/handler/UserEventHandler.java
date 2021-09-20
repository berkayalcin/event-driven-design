package com.estore.userservice.query.handler;

import com.estore.core.model.PaymentDetail;
import com.estore.core.model.User;
import com.estore.core.query.FetchUserPaymentDetailsQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserEventHandler {
    private final List<PaymentDetail> paymentDetails = new ArrayList<>();

    @QueryHandler
    public User findUserPaymentDetails(final FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery) {
        final var paymentDetail = PaymentDetail.builder()
                .cardNumber("123Card")
                .cvv("123")
                .name("Berkay YALCIN")
                .validUntilMonth(12)
                .validUntilYear(2030)
                .build();

        return User.builder()
                .firstName("Berkay")
                .lastName("YALCIN")
                .userId(fetchUserPaymentDetailsQuery.getUserId())
                .paymentDetail(paymentDetail)
                .build();
    }
}
