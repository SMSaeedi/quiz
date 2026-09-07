package com.example.demo.onlinequiz.affirm;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class DistinctPIIValuesCounter {
    private final Set<String> piiSet = new HashSet<>();
    private static final String[] pii_keys = {
            "address", "phone", "email", "ssn"
    };

    public void handleEvent(Event event) {
        Map<String, String> details = event.customerDetails();
        if (details == null)
            return;

        if (!"underwriting".equalsIgnoreCase(event.eventType()))
            return;

        for (String key : pii_keys) {
            String val = details.get(key);
            if (val != null && !val.trim().isEmpty())
                piiSet.add(val);
        }

        // for(Map.Entry<String, String> e : details.entrySet()){
        //     String v=e.getValue();
        //     if(v!=null && !v.trim().isEmpty()){
        //         piiSet.add(v);
        //     }
        // }

        // piiSet.add(details.getOrDefault("address", ""));
        // piiSet.add(details.getOrDefault("phone", ""));
        // piiSet.add(details.getOrDefault("email", ""));
        // piiSet.add(details.getOrDefault("ssn", ""));
    }

    public int getTotalUniquePiiValues() {
        return piiSet.size();
    }
}

// change code above this line
class Solution {
    public static void main(String[] args) throws IOException {
        var service = new DistinctPIIValuesCounter();

        String input = new String(System.in.readAllBytes(), StandardCharsets.UTF_8);

        Type eventType = new TypeToken<List<Event>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        List<Event> events = gson.fromJson(input, eventType);

        events.forEach(service::handleEvent);

        System.out.println(service.getTotalUniquePiiValues());
    }
}