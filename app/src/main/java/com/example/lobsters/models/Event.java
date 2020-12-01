package com.example.lobsters.models;

import com.example.lobsters.R;

public class Event {

    public static final int[] backgrounds = {
            R.drawable.demo_artist_1,
            R.drawable.demo_artist_2,
            R.drawable.demo_artist_3,
            R.drawable.demo_artist_4,
            R.drawable.demo_artist_5
    };

    public String name;
    public String address;
    public String price;
    public String date;
    public String tag; //todo multiple tags
    public int locationBackground;

    public Event(int locationBackground, String locationName, String locationAddress, String price, String date, String tag) {
        this.name = locationName;
        this.address = locationAddress;
        this.locationBackground = locationBackground;
        this.price = price;
        this.date = date;
        this.tag = tag;
    }
}
