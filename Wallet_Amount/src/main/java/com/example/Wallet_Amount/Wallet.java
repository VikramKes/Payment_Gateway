package com.example.Wallet_Amount;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
        private String currency;

    @Column(nullable = false)
    private String userid;

    private Date update_at=new Date();

    public Wallet(Double balance,String currency,String userid,Date update_at){
        this.balance=balance;
        this.currency=currency;
        this.userid=userid;
        this.update_at=update_at;
    }

    public Wallet(){

    }

    public Long getId(){
        return id;

    }

    public void setId(Long id){
        this.id=id;
    }

    public Double getBalance(){
        return balance;
    }

    public void setBalance(Double balance){
        this.balance=balance;
    }

    public String getCurrency(){
        return currency;
    }

    public void setCurrency(String currency){
        this.currency=currency;
    }

    public String getUserid(){
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}
