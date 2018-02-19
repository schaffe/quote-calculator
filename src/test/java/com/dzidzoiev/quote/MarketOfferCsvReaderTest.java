package com.dzidzoiev.quote;

import com.dzidzoiev.quote.model.MarketOffer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MarketOfferCsvReaderTest {
    @Test
    public void testReadFile() throws Exception {
        MarketOfferCsvReader reader = new MarketOfferCsvReader();
        String path = ClassLoader.getSystemResource("market.csv").getPath();
        List<MarketOffer> marketOffers = reader.readFile(path);
        Assert.assertEquals(marketOffers.size(),7);
    }

}