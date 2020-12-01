package com.example.lobsters.ui.artists;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lobsters.models.Event;

import java.util.ArrayList;
import java.util.List;

public class ArtistsViewModel extends ViewModel {

    private MutableLiveData<List<Event>> events;

    public ArtistsViewModel() {
        events = new MutableLiveData<>();
        events.setValue(new ArrayList<Event>());
    }

    public MutableLiveData<List<Event>> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events.setValue(events);
    }
}