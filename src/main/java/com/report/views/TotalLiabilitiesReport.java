package com.report.views;

import com.report.models.TotalLiabilities;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class TotalLiabilitiesReport implements Report {

    private String title = "Total Liabilities";
    private List<TotalLiabilities> totalLiabilitiesList = new ArrayList<>();

    private String [] columns = {"Currency", "#Bets", "Total Stake", "Total Liability"};

    public TotalLiabilitiesReport(List<TotalLiabilities> totalLiabilities){
        this.totalLiabilitiesList = totalLiabilities;
    }

    public String getTitle() {
        return title;
    }

    public List<TotalLiabilities> getTotalLiabilitiesList() {
        return totalLiabilitiesList;
    }

    @Override
    public void display() {
        System.out.println(StringUtils.repeat('#', 45));
        System.out.format("Report Name: %30s %n", getTitle());
        System.out.println(StringUtils.repeat('#', 45) + "\n\n");


        System.out.format("|%-10s | %-15s | %-15s | %-15s%n", columns[0].toUpperCase(), columns[1].toUpperCase(), columns[2].toUpperCase(), columns[3].toUpperCase());
        for(TotalLiabilities liabilities: totalLiabilitiesList){

            String stake = String.format("%s%.2f", getCurrencySymbol(liabilities.getCurrency()), liabilities.getTotalStake());
            String liability = String.format("%s%.2f", getCurrencySymbol(liabilities.getCurrency()), liabilities.getTotalLiability());
            System.out.format(" %-10s | %-15d | %-15s | %-15s%n",liabilities.getCurrency(), liabilities.getNoOfBets(),
                    stake, liability);
        }
    }

    private String getCurrencySymbol(String currency) {
        currency = currency.trim();
        Locale.setDefault(Locale.UK);
        return Currency.getInstance(currency).getSymbol();
    }

    public static void main(String[] args) {
        List<TotalLiabilities> totalLiabilities =
                new ArrayList<>();
        TotalLiabilities t1 = new TotalLiabilities("EUR", 4, 200.0, 500);
        TotalLiabilities t2 = new TotalLiabilities("GBP", 4, 200.0, 500);
        totalLiabilities.add(t1);
        totalLiabilities.add(t2);
        TotalLiabilitiesReport report = new TotalLiabilitiesReport(totalLiabilities);
        report.display();
    }
}
