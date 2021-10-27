package com.example.springgradleexample.prototype;

import java.util.HashMap;
import java.util.Map;

public class RatesPrototype {

    public static Map<String, Double> todayRates() {
        HashMap<String, Double> todayRates = new HashMap<>();
        todayRates.put("EUR", 2.0);
        todayRates.put("RUB", 1.1);
        return todayRates;
    }


    public static Map<String, Double> yesterdayRates() {
        HashMap<String, Double> yesterdayRates = new HashMap<>();
        yesterdayRates.put("EUR", 2.2);
        yesterdayRates.put("RUB", 1.0);
        return yesterdayRates;
    }
}
