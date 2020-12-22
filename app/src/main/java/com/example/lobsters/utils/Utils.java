package com.example.lobsters.utils;

import com.example.lobsters.models.Event;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;

public class Utils {
    public static final LatLng INITIAL_MAP_CAMERA_POS  = new LatLng(56.83837521490325, 60.60333247403461);

    public static HashMap<Long, Event> createEventsMap(List<Event> events) {
        HashMap<Long, Event> result = new HashMap<>();
        for (Event event : events)
            result.put(event.getId(), event);
        return result;
    }
}
