package com.sagarannaldas.online_store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("stripe")
//@Primary
public class StripePaymentService implements PaymentService {

    @Value("${stripe.apiUrl}")
    private String apiURL;

    @Value("${stripe.enabled}")
    private boolean enabled;

    @Value("${stripe.timeout:3000}")
    private int timeout;

    @Value("${stripe.supported-currencies}")
    private List<String> supportedCurrencies;

    @Override
    public void processPayment(double amount) {
        System.out.println("STRIPE");
        System.out.println("Amount " + amount);
        System.out.println("apiUrl " + apiURL);
        System.out.println("Enabled " + enabled);
        System.out.println("Timeout " + timeout);
        System.out.println("Currencies: " + supportedCurrencies);
    }
}
