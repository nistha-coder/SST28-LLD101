package com.example.payments;

public class SafeCashAdapter implements PaymentGateway {
    private final SafeCashClient safeCashClient;

    public SafeCashAdapter(SafeCashClient safeCashClient) {
        this.safeCashClient = safeCashClient;
    }

    @Override
    public String charge(String custId, int amount) {
        SafeCashPayment scp = safeCashClient.createPayment(amount, custId);
        return scp.confirm();
    }
}
