package ru.lebedev.cronachecker.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.lebedev.cronachecker.entity.ExchangeMarketEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class Parser {
    public List<ExchangeMarketEntity> runParse(String url) {
        var trim = getDateFromUrl(url);
        return mapLinesToEntity(parseLinesToList(url), trim);
    }

    @SneakyThrows
    private static List<String> parseLinesToList(String url) {
        var urlAdress = new URL(url);
        URLConnection connection = urlAdress.openConnection();
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

    private static List<ExchangeMarketEntity> mapLinesToEntity(List<String> list, String date) {
        return list.stream()
                .skip(2)
                .map(s -> {
                    var split = s.split("\\|");
                    return new ExchangeMarketEntity(
                            split[0],
                            split[1],
                            Integer.parseInt(split[2]),
                            split[3],
                            new BigDecimal(split[4]),
                            LocalDate.parse(date, DateFormat.FORMATTER),
                            LocalDateTime.now()
                    );
                }).collect(toList());
    }


    private static String getDateFromUrl(String url) {
        return url.substring(url.lastIndexOf('=')).replaceAll("=", "");
    }
}
