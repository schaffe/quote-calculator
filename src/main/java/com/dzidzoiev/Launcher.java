package com.dzidzoiev;

import com.dzidzoiev.model.MarketDataItem;

import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Hello");
        MarketDataCsvReader r = new MarketDataCsvReader();
        List<MarketDataItem> marketDataItems = r.readFile("market.csv");
        System.out.println(marketDataItems);
    }
}
