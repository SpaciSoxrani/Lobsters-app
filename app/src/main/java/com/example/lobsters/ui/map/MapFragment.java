package com.example.lobsters.ui.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.lobsters.utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.HashMap;
import java.util.List;

public class MapFragment extends Fragment {

    private MapViewModel mapViewModel;
    private GoogleMap map;
    private BottomSheetBehavior sheetBehavior;

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;

            //todo set initial camera position from profile
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(Utils.INITIAL_MAP_CAMERA_POS)
                    .zoom(14)
                    .build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(final Marker marker) {
                    mapViewModel.setSelectedMakerIcon(UiUtils.createMarkerIcon(getResources().getColor(R.color.markerDisabled)));
                    mapViewModel.setSelectedMarker(marker);
                    mapViewModel.setSelectedMakerIcon(UiUtils.createMarkerIcon(getResources().getColor(R.color.colorPrimary)));
                    return true;
                }
            });

            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {

                    mapViewModel.setSelectedMakerIcon(UiUtils.createMarkerIcon(getResources().getColor(R.color.markerDisabled)));
                    mapViewModel.setSelectedMarker(null);
                }
            });

            //todo load from server
            List<Event> events = DemoServerApi.EVENTS;
            mapViewModel.setEvents(Utils.createEventsMap(events));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);
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
                mapViewModel.toggleSelectedEventFavourite();

                //todo send server request to make artist favourite for user

                buttonFavourite.setImageDrawable(UiUtils.getFavouriteDrawable(getContext(),mapViewModel.getSelectedEventFavourite()));
            }
        });

        sheetBehavior = BottomSheetBehavior.from(layoutCompat);
        toggleBottomSheet(0, false);


        //todo ASK FOR LOCATION PERMISSION AND SHOW CURRENT LOCATION BUTTON

        //!!! important: don't set events before map is ready
        mapViewModel.getEvents().observe(getViewLifecycleOwner(), new Observer<HashMap<Long, Event>>() {
            @Override
            public void onChanged(HashMap<Long, Event> events) {
                redrawEventsMarkers(events);
            }
        });

        mapViewModel.getSelectedEvent().observe(getViewLifecycleOwner(), new Observer<Event>() {
            @Override
            public void onChanged(Event event) {
                if(event==null) {
                    toggleBottomSheet(0, true);
                    return;
                }
                toggleBottomSheet(getResources().getDimensionPixelSize(R.dimen.bottomsheet_event_peek_height), true);
                textViewName.setText(event.name);
                textViewDescription.setText(event.description);
                textViewDate.setText(event.date);
                textViewPrice.setText(event.price);
                textViewAddress.setText(event.address);
                imageViewEvent.setImageDrawable(getResources().getDrawable(Event.backgrounds[event.image]));
                buttonFavourite.setImageDrawable(UiUtils.getFavouriteDrawable(getContext(),mapViewModel.getSelectedEventFavourite()));
            }
        });

        mapViewModel.getSelectedMarker().observe(getViewLifecycleOwner(), new Observer<Marker>() {
            @Override
            public void onChanged(final Marker marker) {

                if(marker == null) {
                    mapViewModel.setSelectedEvent(null);
                    return;
                }

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(marker.getPosition())
                        .zoom(map.getCameraPosition().zoom > 14 ? map.getCameraPosition().zoom : 14)
                        .build();
                //move camera to marker
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 400, new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        Log.e("camera", "MOVED");

                        //todo show bottomSheet after camera moved to marker
                        long id = Long.parseLong(marker.getSnippet());
                        Event event = mapViewModel.getEvents().getValue().get(id);
                        mapViewModel.setSelectedEvent(event);
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });
        //setHasOptionsMenu(true);
        return root;
    }

    private void toggleBottomSheet(int height, boolean shouldAnimate) {
        sheetBehavior.setPeekHeight(height, shouldAnimate);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void redrawEventsMarkers(HashMap<Long, Event> events) {
        if (map == null)
            return;
        for (Event event : events.values()) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .icon(UiUtils.createMarkerIcon(getResources().getColor(R.color.markerDisabled)))
                    .position(event.getLatLng())
                    .snippet(String.valueOf(event.getId())); //snippet is used for marker tracking
            map.addMarker(markerOptions);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}