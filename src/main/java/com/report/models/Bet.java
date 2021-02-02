package com.report.models;

import com.opencsv.bean.CsvBindByName;

public class Bet {

    @CsvBindByName
    private String betId;

    @CsvBindByName
    private String betTimestamp;

    @CsvBindByName
    private String selectionId;

    @CsvBindByName
    private String selectionName;

    @CsvBindByName
    private double stake;

    @CsvBindByName
    private double price;

    @CsvBindByName
    private String currency;

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Bets{" +
                "betId='" + betId + '\'' +
                ", betTimestamp='" + betTimestamp + '\'' +
                ", selectionId=" + selectionId +
                ", selectionName='" + selectionName + '\'' +
                ", stake=" + stake +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }

    public double getStake() {
        return stake;
    }

    public double getPrice() {
        return price;
    }

    public String getSelectionId() {
        return selectionId;
    }

    public String getSelectionName() {
        return selectionName;
    }
}
