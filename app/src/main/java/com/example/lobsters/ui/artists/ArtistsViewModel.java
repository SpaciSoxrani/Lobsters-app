package com.example.lobsters.ui.artists;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lobsters.models.Event;

import java.util.ArrayList;
import java.util.List;

public class ArtistsViewModel extends ViewModel {

    private MutableLiveData<List<Event>> events;
    private MutableLiveData<Event> selectedEvent;

    public ArtistsViewModel() {
        events = new MutableLiveData<>();
        events.setValue(new ArrayList<Event>());
        selectedEvent = new MutableLiveData<>();
        selectedEvent.setValue(null);
    }

    public MutableLiveData<List<Event>> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events.setValue(events);
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
        if(event!=null)
            event.toggleFavourite();
    }

    public boolean getSelectedEventFavourite() {
        Event event = this.getSelectedEvent().getValue();
        if(event==null)
            return false;
        return event.isFavourite;
    }

    public void toggleSelectedEventFavouriteWithUpdate() {
        toggleSelectedEventFavourite(); //change event value
        setEvents(getEvents().getValue()); //update values and toggle list redraw
    }
}