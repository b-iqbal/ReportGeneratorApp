package com.report.models;

public class TotalLiabilities {

    private String currency;
    private int noOfBets;
    private double totalStake;
    private double totalLiability;

    public TotalLiabilities(String currency, int noOfBets, double totalStake, double totalLiability){
        this.currency = currency;
        this.noOfBets = noOfBets;
        this.totalStake = totalStake;
        this.totalLiability = totalLiability;
    }


    public String getCurrency() {
        return currency;
    }

    public int getNoOfBets() {
        return noOfBets;
    }

    public double getTotalStake() {
        return totalStake;
    }

    public double getTotalLiability() {
        return totalLiability;
    }
}
