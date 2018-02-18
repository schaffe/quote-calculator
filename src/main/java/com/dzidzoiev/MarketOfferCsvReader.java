package com.dzidzoiev;

import com.dzidzoiev.model.MarketOffer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MarketOfferCsvReader {
    public List<MarketOffer> readFile(String path) {
        try (Reader in = new FileReader(path)) {
            final CSVParser records = new CSVParser(in, CSVFormat.DEFAULT.withHeader());
            return StreamSupport.stream(records.spliterator(), false)
                    .map(this::toMarketOffer)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private MarketOffer toMarketOffer(CSVRecord record) {
        return new MarketOffer(
                record.get("Lender"),
                new BigDecimal(record.get("Rate")),
                new BigDecimal(record.get("Available")));
    }
}
