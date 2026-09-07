package com.example.demo.affirm;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void countsFourPiiValuesFromEachUnderwritingEvent() {
        DistinctPIIValuesCounter counter = new DistinctPIIValuesCounter();

        counter.handleEvent(event("underwriting", "779 Brady Way",
                "736-811-6504", "bullard.donnelly@hotmail.com", "818157191"));
        counter.handleEvent(event("underwriting", "4985 Barker Ave",
                "612-591-1987", "clark.pridgen@yahoo.com", "766059118"));

        assertEquals(8, counter.getTotalUniquePiiValues());
    }

    @Test
    void ignoresFraudFlagEvents() {
        DistinctPIIValuesCounter counter = new DistinctPIIValuesCounter();

        counter.handleEvent(event("fraud_flag", "779 Brady Way",
                "736-811-6504", "bullard.donnelly@hotmail.com", "818157191"));

        assertEquals(0, counter.getTotalUniquePiiValues());
    }

    @Test
    void countsRepeatedPiiValuesOnlyOnce() {
        DistinctPIIValuesCounter counter = new DistinctPIIValuesCounter();
        Event event = event("underwriting", "779 Brady Way",
                "736-811-6504", "bullard.donnelly@hotmail.com", "818157191");

        counter.handleEvent(event);
        counter.handleEvent(event);

        assertEquals(4, counter.getTotalUniquePiiValues());
    }

    @Test
    void skipsMissingAndBlankPiiValues() {
        DistinctPIIValuesCounter counter = new DistinctPIIValuesCounter();
        Event event = new Event("underwriting", null, Map.of(
                "address", " ",
                "phone", "736-811-6504",
                "email", "",
                "ssn", "818157191"));

        counter.handleEvent(event);

        assertEquals(2, counter.getTotalUniquePiiValues());
    }

    private Event event(String type, String address, String phone,
                        String email, String ssn) {
        return new Event(type, null, Map.of(
                "address", address,
                "phone", phone,
                "email", email,
                "ssn", ssn));
    }
}
