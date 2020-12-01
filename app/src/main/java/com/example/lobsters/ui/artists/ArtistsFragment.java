package com.example.lobsters.ui.artists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lobsters.R;
import com.example.lobsters.models.Event;
import com.example.lobsters.serverapi.DemoServerApi;

import java.util.List;

public class ArtistsFragment extends Fragment {

    private ArtistsViewModel artistsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        artistsViewModel =
                ViewModelProviders.of(this).get(ArtistsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);
        final LinearLayoutCompat cardsContainer = root.findViewById(R.id.card_container);
        artistsViewModel.getEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                redrawEventsCards(events,cardsContainer);
            }
        });

        //todo load from server
        artistsViewModel.setEvents(DemoServerApi.EVENTS);

        return root;
    }

    //todo use recyclerview
    private void redrawEventsCards(List<Event> events, LinearLayoutCompat container) {
        container.removeAllViews();
        for (int i = 0; i < events.size(); i++) {
            Event loc = events.get(i);
            View card = LayoutInflater.from(getContext()).inflate(R.layout.card_events, container, false);
            AppCompatImageView imageView = card.findViewById(R.id.location_bg);
            AppCompatTextView textViewName = card.findViewById(R.id.text_name);
            AppCompatTextView textViewAddress = card.findViewById(R.id.text_address);
            AppCompatTextView textViewPrice = card.findViewById(R.id.text_price);
            AppCompatTextView textViewDate = card.findViewById(R.id.text_date);
            imageView.setImageResource(Event.backgrounds[loc.locationBackground]);
            textViewName.setText(loc.name);
            textViewAddress.setText(loc.address);
            textViewPrice.setText(loc.price);
            textViewDate.setText(loc.date);
            container.addView(card);
        }
    }
}