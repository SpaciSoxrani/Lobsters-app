package com.example.lobsters.models;

import com.example.lobsters.R;
import com.google.android.gms.maps.model.LatLng;

public class Event {

    public static final int[] backgrounds = {
            R.drawable.demo_artist_1,
            R.drawable.demo_artist_2,
            R.drawable.demo_artist_3,
            R.drawable.demo_artist_4,
            R.drawable.demo_artist_5
    };

    // !!! NAME IS UNIQUE AND IS USED AS ID
    public String name;
    public String description;
    public String address;
    public String price;
    public String date;
    public String tag; //todo multiple tags
    public int image;
    public boolean isFavourite;

    private final double latitude;
    private final double longitude;

    public long getId() {
        return id;
    }

    private final long id;

    public Event(int image, String name, String description, String address, String price, String date, String tag, boolean isFavourite, double latitude, double longitude,long id) {
        this.name = name;
        this.description=description;
        this.address = address;
        this.image = image;
        this.price = price;
        this.date = date;
        this.tag = tag;
        this.isFavourite=isFavourite;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id=id;
    }

    public LatLng getLatLng(){
        return new LatLng(latitude,longitude);
    }

    public void toggleFavourite() {
        isFavourite = !isFavourite;
    }
}
