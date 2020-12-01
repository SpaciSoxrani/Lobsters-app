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
            "Pop", "Rap", "Industrial metal", "Hardcore","Electronics"
    };

    public static final List<Event> EVENTS = new ArrayList<Event>() {{

        add(new Event(0, "Мальбек", "Выхино", "800Р", "12 Ноября, 19:40", TAGS[0]));
        add(new Event(1, "Фараон", "Эльмаш", "400Р", "15 Ноября, 19:50", TAGS[1]));
        add(new Event(2, "Nine Inch Nails", "Уралмаш", "900Р", "19 Декабря, 18:20", TAGS[2]));
        add(new Event(3, "Fugazi", "Химмаш", "500Р", "24 Декабря, 20:10", TAGS[3]));
        add(new Event(4, "Жока и Бока", "Дк Железнодорожников", "2000Р", "28 Декабря, 19:00", TAGS[4]));
    }};

    public static boolean checkSignIn(String login, String password) {
        return CORRECT_LOGIN_PAIRS.containsKey(login) && Objects.equals(CORRECT_LOGIN_PAIRS.get(login), password);
    }
}
