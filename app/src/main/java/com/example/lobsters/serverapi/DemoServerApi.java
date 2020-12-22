package com.example.lobsters.serverapi;

import com.example.lobsters.models.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DemoServerApi {

    public static final Map<String, String> CORRECT_LOGIN_PAIRS = new HashMap<String, String>() {
        {
            put("логин", "пароль");
            put("настя", "смирнова");
            put("1", "1");
        }
    };

    public static final String[] TAGS = new String[]{
            "Pop", "Rap", "Industrial metal", "Hardcore", "Electronics"
    };

    private static final String DEFAULT_DESCRIPTION = "Описание концерта с очень важной информацией об артисте длиной в две строки";

    public static final List<Event> EVENTS = new ArrayList<Event>() {{

        add(new Event(0, "Мальбек", DEFAULT_DESCRIPTION, "Парк \"Динамо\"", "800Р", "27 Декабря, 19:40", TAGS[0], false, 56.84465330372982, 60.60098572668523, 0));
        add(new Event(1, "Фараон", DEFAULT_DESCRIPTION, "Дзержинского, 2", "400Р", "28 Декабря, 19:50", TAGS[1], false, 56.84537502599262, 60.60479982791574, 1));
        add(new Event(2, "Nine Inch Nails", DEFAULT_DESCRIPTION, "Октябрьская Площадь", "900Р", "29 Декабря, 18:20", TAGS[2], true, 56.84215357250128, 60.59728964274724, 2));
        add(new Event(3, "Fugazi", DEFAULT_DESCRIPTION, "Проспект Ленина", "500Р", "26 Декабря, 20:10", TAGS[3], true, 56.83792829519901, 60.60273989145872, 3));
        add(new Event(4, "Жока и Бока", DEFAULT_DESCRIPTION, "пл. Октябрьская, 2", "2000Р", "31 Декабря, 19:00", TAGS[4], false, 56.84362643892394, 60.59389933062778, 4));
    }};

    /*public static final HashMap<Long,Event> EVENTS_MAP = new HashMap<Long, Event>() {{
        put(0L,new Event(0, "Мальбек", "Парк \"Динамо\"", "800Р", "27 Декабря, 19:40", TAGS[0],56.84465330372982, 60.60098572668523,0));
        put(1L,new Event(1, "Фараон", "Дзержинского, 2", "400Р", "28 Декабря, 19:50", TAGS[1],56.84537502599262, 60.60479982791574,1));
        put(2L,new Event(2, "Nine Inch Nails", "Октябрьская Площадь", "900Р", "29 Декабря, 18:20", TAGS[2],56.84215357250128, 60.59728964274724,2));
        put(3L,new Event(3, "Fugazi", "Проспект Ленина", "500Р", "26 Декабря, 20:10", TAGS[3],56.83792829519901, 60.60273989145872,3));
        put(4L,new Event(4, "Жока и Бока", "пл. Октябрьская, 2", "2000Р", "31 Декабря, 19:00", TAGS[4],56.84362643892394, 60.59389933062778,4));
    }};*/


    public static boolean checkSignIn(String login, String password) {
        return CORRECT_LOGIN_PAIRS.containsKey(login) && Objects.equals(CORRECT_LOGIN_PAIRS.get(login), password);
    }

    public static final String[] NAMES = new String[]{"Смирнова Настенька"};
    public static final String[] LOCATION = new String[]{
            "Россия, Москва", "Россия, Екатеринбург"
    };

}
