package com.example.Wallet_Amount;

public class WalletRequest {
    private String userId;
    private String currency;

    public WalletRequest(){

    }

    public WalletRequest(String userId, String currency) {
        this.userId = userId;
        this.currency = currency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
