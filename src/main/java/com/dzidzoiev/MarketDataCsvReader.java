package com.dzidzoiev;

import com.dzidzoiev.model.MarketDataItem;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MarketDataCsvReader {
    public List<MarketDataItem> readFile(String path) {
        try (Reader in = new FileReader(path)) {
            final CSVParser records = new CSVParser(in, CSVFormat.DEFAULT.withHeader());
            return StreamSupport.stream(records.spliterator(), false)
                    .map(this::toMarketDataItem)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private MarketDataItem toMarketDataItem(CSVRecord record) {
        return new MarketDataItem(
                record.get("Lender"),
                Double.parseDouble(record.get("Rate")),
                Integer.parseInt(record.get("Available")));
    }
}
