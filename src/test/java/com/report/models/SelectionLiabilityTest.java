package com.report.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionLiabilityTest {

    private SelectionLiability selectionLiability;

    //Data driven junit test

    @BeforeEach
    public void setUp(){
        selectionLiability = new SelectionLiability("Le Blanc", "EUR",
                2, 100);
    }

    @Test
    void getSelectionName() {
        assertEquals("Le Blanc", selectionLiability.getSelectionName());
    }

    @Test
    void getCurrency() {
        assertEquals("EUR", selectionLiability.getCurrency());
    }

    @Test
    @DisplayName("Ensure bet is 1 at initialization ")
    void getNoOfBets() {
        assertEquals(1, selectionLiability.getNoOfBets());
    }

    @Test
    void getTotalStake() {
        assertEquals(2, selectionLiability.getTotalStake());
    }

    @Test
    void getTotalLiability() {
        assertEquals(100, selectionLiability.getTotalLiability());
    }

    @Test
    @DisplayName("Ensure liability under same currency is updated")
    void updateData() {
        selectionLiability.updateData(4, 10);
        assertEquals(2, selectionLiability.getNoOfBets());
        assertEquals(6, selectionLiability.getTotalStake());
        assertEquals(140, selectionLiability.getTotalLiability());
    }

    @Test
    void setTotalLiability() {
        selectionLiability.setTotalLiability(300);
        assertEquals(300, selectionLiability.getTotalLiability());
    }

    @Test
    void setTotalStake() {
        selectionLiability.setTotalStake(200);
        assertEquals(200, selectionLiability.getTotalStake());
    }
}