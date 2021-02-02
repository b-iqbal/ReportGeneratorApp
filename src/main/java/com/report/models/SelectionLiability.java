package com.report.models;

public class SelectionLiability {

    private String selectionName;
    private String currency;
    private int noOfBets;
    private double totalStake;
    private double totalLiability;

    public SelectionLiability(String selectionName, String currency, double totalStakes,
                              double totalLiability){
        this.selectionName = selectionName;
        this.currency = currency;
        this.noOfBets = 1;
        this.totalStake = totalStakes;
        this.totalLiability = totalLiability;
    }

    public String getSelectionName() {
        return selectionName;
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

    public void updateData(double stake, double price) {
        noOfBets += 1;
        setTotalStake(getTotalStake() + stake);
        setTotalLiability(getTotalLiability() + (stake * price));
    }


    public void setTotalLiability(double totalLiability) {
        this.totalLiability = totalLiability;
    }

    public void setTotalStake(double totalStake) {
        this.totalStake = totalStake;
    }

}
