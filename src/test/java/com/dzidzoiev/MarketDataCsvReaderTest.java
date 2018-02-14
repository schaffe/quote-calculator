package com.dzidzoiev;

import com.dzidzoiev.model.MarketDataItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MarketDataCsvReaderTest {
    @Test
    public void testReadFile() throws Exception {
        MarketDataCsvReader reader = new MarketDataCsvReader();
        String path = ClassLoader.getSystemResource("market.csv").getPath();
        List<MarketDataItem> marketDataItems = reader.readFile(path);
        Assert.assertEquals(marketDataItems.size(),7);
    }

}