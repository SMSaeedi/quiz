package com.example.demo.eventquiz;

import java.util.ArrayList;
import java.util.List;

public class EventQuiz {
    private static List<Event> events = new ArrayList<>();

    public static void main(String[] args) {
        events.add(new Event("foo", 1000000L));
        events.add(new Event("bar", 1000003L));
        events.add(new Event("baz", 1000006L));
        events.add(new Event("foo", 1000009L));
        events.add(new Event("bar", 1000013L));
        events.add(new Event("baz", 1000016L));
        events.add(new Event("foo", 1000013L));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < events.size(); i++)
            for (int j = i + 1; j < events.size(); j++)
                if (events.get(j).getName().equals(events.get(i).getName())) {
                    result.add(events.get(i).getName());
                    if (events.get(j).getTimestamp() - events.get(i).getTimestamp() >= 10L) {
                        j++;
                        System.out.println(events.get(i).getName());
                    }
                }

    }
}

class Event {
    private String name;
    private Long timestamp;

    public Event(String name, Long timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }
    public String getName() {
        return name;
    }
    public Long getTimestamp() {
        return timestamp;
    }
}
