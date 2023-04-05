package ru.lebedev.cronachecker.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.lebedev.cronachecker.entity.ExchangeMarketEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class Parser {

    public static final String ADRESS = "https://www.cnb.cz/en/financial_markets/foreign_exchange_market/exchange_rate_fixing/daily.txt?date=";
    public static final String URL = ADRESS + "05.04.2023";



    public List<ExchangeMarketEntity> runParse(){
       return mapLinesToEntity(parseLinesToList(URL));
    }
    @SneakyThrows
    private static List<String> parseLinesToList(String urlPath) {
        java.net.URL url = new URL(urlPath);
        URLConnection connection = url.openConnection();
        var inputStream = connection.getInputStream();
        var lines = new ArrayList<String>();
        try (var bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static List<ExchangeMarketEntity> mapLinesToEntity(List<String> list) {
        return list.stream()
                .skip(2)
                .map(s -> {
                    var split = s.split("\\|");
                    return new ExchangeMarketEntity(
                            split[0],
                            split[1],
                            Integer.parseInt(split[2]),
                            split[3],
                            new BigDecimal(split[4]));
                }).collect(toList());
    }

}
