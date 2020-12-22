package com.example.lobsters.ui.artists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lobsters.R;
import com.example.lobsters.models.Event;
import com.example.lobsters.serverapi.DemoServerApi;
import com.example.lobsters.utils.UiUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

public class ArtistsFragment extends Fragment {

    private ArtistsViewModel artistsViewModel;
    private BottomSheetBehavior sheetBehavior;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        artistsViewModel =
                ViewModelProviders.of(this).get(ArtistsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        LinearLayoutCompat layoutCompat = root.findViewById(R.id.event_bottomsheet);
        final AppCompatTextView textViewName = root.findViewById(R.id.text_name);
        final AppCompatTextView textViewDescription = root.findViewById(R.id.text_description);
        final AppCompatTextView textViewDate = root.findViewById(R.id.text_date);
        final AppCompatTextView textViewPrice = root.findViewById(R.id.text_price);
        final AppCompatTextView textViewAddress = root.findViewById(R.id.text_address);
        final AppCompatImageView imageViewEvent = root.findViewById(R.id.image_event);
        final AppCompatImageButton buttonFavourite = root.findViewById(R.id.button_favourite);
        buttonFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artistsViewModel.toggleSelectedEventFavouriteWithUpdate();

                //todo send server request to make artist favourite for user

                buttonFavourite.setImageDrawable(UiUtils.getFavouriteDrawable(getContext(),artistsViewModel.getSelectedEventFavourite()));
            }
        });

        sheetBehavior = BottomSheetBehavior.from(layoutCompat);
        sheetBehavior.setHideable(true); // sheet can be manually hidden in artists fragment
        sheetBehavior.setPeekHeight(0,false);

        final LinearLayoutCompat cardsContainer = root.findViewById(R.id.card_container);

        artistsViewModel.getEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                redrawEventsCards(events,cardsContainer);
            }
        });

        artistsViewModel.getSelectedEvent().observe(getViewLifecycleOwner(), new Observer<Event>() {
            @Override
            public void onChanged(Event event) {
                if(event==null)
                    return;
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                textViewName.setText(event.name);
                textViewDescription.setText(event.description);
                textViewDate.setText(event.date);
                textViewPrice.setText(event.price);
                textViewAddress.setText(event.address);
                imageViewEvent.setImageDrawable(getResources().getDrawable(Event.backgrounds[event.image]));
                buttonFavourite.setImageDrawable(UiUtils.getFavouriteDrawable(getContext(),artistsViewModel.getSelectedEventFavourite()));
            }
        });

        //todo load from server
        artistsViewModel.setEvents(DemoServerApi.EVENTS);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.events_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_settings) {
            //todo advanced search dialog
            UiUtils.ShowSimpleDialog("Поиск","Поиск артиста по названию, времени и тегам",getContext());
        }
        return super.onOptionsItemSelected(item);
    }

    //todo use recyclerview
    private void redrawEventsCards(List<Event> events, LinearLayoutCompat container) {
        container.removeAllViews();
        for (int i = 0; i < events.size(); i++) {
            final Event event = events.get(i);
            View card = LayoutInflater.from(getContext()).inflate(R.layout.card_events, container, false);
            AppCompatImageView imageView = card.findViewById(R.id.location_bg);
            AppCompatTextView textViewName = card.findViewById(R.id.text_name);
            AppCompatTextView textViewAddress = card.findViewById(R.id.text_address);
            AppCompatTextView textViewPrice = card.findViewById(R.id.text_price);
            AppCompatTextView textViewDate = card.findViewById(R.id.text_date);
            final AppCompatImageButton buttonFavourite = card.findViewById(R.id.button_favourite);
            imageView.setImageResource(Event.backgrounds[event.image]);
            textViewName.setText(event.name);
            textViewAddress.setText(event.address);
            textViewPrice.setText(event.price);
            textViewDate.setText(event.date);
            buttonFavourite.setImageDrawable(UiUtils.getFavouriteDrawable(getContext(),event.isFavourite));
            buttonFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo send server request to make artist favourite for user
                    event.toggleFavourite(); //changes event in list struct but doesn't call observer
                    buttonFavourite.setImageDrawable(UiUtils.getFavouriteDrawable(getContext(),event.isFavourite));
                }
            });
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    artistsViewModel.setSelectedEvent(event);
                }
            });
            container.addView(card);
        }
    }
}