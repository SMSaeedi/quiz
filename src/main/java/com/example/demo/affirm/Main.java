package com.example.demo.affirm;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

record Event(
        @SerializedName("event_type") String eventType,
        @SerializedName("loan_amount") Integer loanAmount,
        @SerializedName("customer_details") Map<String, String> customerDetails
) {
}

class FraudDetector {
    private final Set<String> suspiciousPiiSet = new HashSet<>();
    private static final String[] pii_keys = {
            "address", "phone", "email", "ssn"
    };

    public String handleEvent(Event event) {
        if (event == null || event.eventType() == null) return "";

        String type = event.eventType();
        Map<String, String> details = event.customerDetails();

        if ("fraud_flag".equalsIgnoreCase(type)) {
            if (details != null) {
                for (String key : pii_keys) {
                    String val = details.get(key);
                    if (val != null && !val.trim().isEmpty())
                        suspiciousPiiSet.add(val.trim());
                }
            }
            return "";
        }

        boolean isFraud = false;
        if ("underwriting".equalsIgnoreCase(type)) {
            if (details != null) {
                for (String key : pii_keys) {
                    String val = details.get(key);
                    if (val != null && !val.trim().isEmpty())
                        if (suspiciousPiiSet.contains(val.trim()))
                            isFraud = true;
                }
            }

            if (isFraud) {
                for (String key : pii_keys) {
                    String val = details.get(key);
                    if (val != null && !val.trim().isEmpty())
                        suspiciousPiiSet.add(val.trim());
                }
            }
        }

        return isFraud ? "1" : "0";
    }
}

// change the code above this line
public class Main {
    public static void main(String[] args) throws IOException {
        var service = new FraudDetector();

        String input = new String(System.in.readAllBytes(), StandardCharsets.UTF_8);

        Type eventListType = new TypeToken<List<Event>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        List<Event> events = gson.fromJson(input, eventListType);

        events.forEach(event -> System.out.println(service.handleEvent(event)));
    }
}