package com.example.lobsters.ui.map;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lobsters.models.Event;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

public class MapViewModel extends ViewModel {

    private MutableLiveData<HashMap<Long,Event>> eventsMap;
    private MutableLiveData<Marker> selectedMarker;
    private MutableLiveData<Event> selectedEvent;

    public MapViewModel() {
        eventsMap = new MutableLiveData<>();
        eventsMap.setValue(new HashMap<Long, Event>());
        selectedMarker = new MutableLiveData<>();
        selectedMarker.setValue(null);
        selectedEvent = new MutableLiveData<>();
        selectedEvent.setValue(null);
    }

    public MutableLiveData<HashMap<Long,Event>> getEvents() {
        return eventsMap;
    }

    public void setEvents(HashMap<Long,Event> events) {
        this.eventsMap.setValue(events);
    }

    public MutableLiveData<Marker> getSelectedMarker() {
        return selectedMarker;
    }

    public void setSelectedMarker(Marker marker) {
        this.selectedMarker.setValue(marker);
    }

    public void setSelectedMakerIcon(BitmapDescriptor icon) {
        Marker marker = this.selectedMarker.getValue();
        if(marker!=null)
            marker.setIcon(icon);
    }

    public MutableLiveData<Event> getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event event) {
        this.selectedEvent.setValue(event);
    }

    //also changes favourite in hashmap but without calling observer (map doesn't need to be redrawed)
    public void toggleSelectedEventFavourite() {
       Event event = this.getSelectedEvent().getValue();
       if(event!=null) {
           event.toggleFavourite();
       }
    }

    public boolean getSelectedEventFavourite() {
        Event event = this.getSelectedEvent().getValue();
        if(event==null)
           return false;
        return event.isFavourite;
    }
}