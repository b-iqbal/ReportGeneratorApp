package com.report.views;

import com.report.models.SelectionLiability;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class SelectionLiabilitiesReport implements Report {

    private String title = "Selection Liabilities";

    String [] columns = {"Selection Name", "Currency", "#Bets", "Total Stake", "Total Liability"};

    private List<SelectionLiability> selectionLiabilityList;

    public SelectionLiabilitiesReport(List<SelectionLiability> selectionLiabilities){
        this.selectionLiabilityList = selectionLiabilities;
    }

    public String getTitle() {
        return title;
    }

    public List<SelectionLiability> getSelectionLiabilityList() {
        return selectionLiabilityList;
    }

    @Override
    public void display() {
        System.out.println(StringUtils.repeat('#', 45));
        System.out.format("Report Name: %30s %n", getTitle());
        System.out.println(StringUtils.repeat('#', 45) + "\n\n");


        System.out.format("|%-20s | %-10s | %-10s | %-15s | %-15s%n", columns[0].toUpperCase(), columns[1].toUpperCase(), columns[2].toUpperCase(), columns[3].toUpperCase(),
                columns[4].toUpperCase());
        for (SelectionLiability selectionLiability : selectionLiabilityList) {

            String symbol = getCurrencySymbol(selectionLiability.getCurrency());
            String stake = String.format("%s%.2f", symbol, selectionLiability.getTotalStake());
            String liability = String.format("%s%.2f", symbol, selectionLiability.getTotalLiability());
            System.out.format(" %-20s | %-10s | %-10d | %-15s | %-15s%n", selectionLiability.getSelectionName(), selectionLiability.getCurrency(),
                    selectionLiability.getNoOfBets(), stake, liability);

        }
    }

    private String getCurrencySymbol(String currency) {
        currency = currency.trim();
        Locale.setDefault(Locale.UK);
        return Currency.getInstance(currency).getSymbol();
    }

    public static void main(String[] args) {
        SelectionLiability sl1 = new SelectionLiability("John", "EUR",  245, 443);
        SelectionLiability sl2 = new SelectionLiability("John", "GBP", 233.45, 888);
        SelectionLiability sl3 = new SelectionLiability("James", "EUR", 245, 443);

        List<SelectionLiability> selectionLiabilities = new ArrayList<>();
        selectionLiabilities.add(sl1);
        selectionLiabilities.add(sl2);
        selectionLiabilities.add(sl3);

        SelectionLiabilitiesReport report = new SelectionLiabilitiesReport(selectionLiabilities);
        report.display();
    }
}
