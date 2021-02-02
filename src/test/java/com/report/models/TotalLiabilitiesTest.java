package com.report.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalLiabilitiesTest {

    private TotalLiabilities totalLiabilities;

    @BeforeEach
    public void setUp(){
        totalLiabilities = new TotalLiabilities("EUR", 1, 199.9, 555.5);
    }

    @Test
    @DisplayName("Get Currency")
    public void testGetCurrency(){
        assertEquals("EUR", totalLiabilities.getCurrency());
    }

    @Test
    @DisplayName("Get number of Bets")
    public void testGetNoOfBets(){
        assertEquals(1, totalLiabilities.getNoOfBets());
    }

    @Test
    @DisplayName("Get total stake")
    public void testGetTotalStakes(){
        assertEquals(199.9, totalLiabilities.getTotalStake());
    }

    @Test
    @DisplayName("Get total liability")
    public void testGetTotalLiability(){
        assertEquals(555.5, totalLiabilities.getTotalLiability());
    }


}