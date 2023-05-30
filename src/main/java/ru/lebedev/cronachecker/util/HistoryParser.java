package ru.lebedev.cronachecker.util;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import ru.lebedev.cronachecker.entity.HistoryExcangeMarket;
import ru.lebedev.cronachecker.entity.HistoryExcangeMarketEntity;
import ru.lebedev.cronachecker.entity.HistoryExcangeMarketEntityWithOutRubAndHrk;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class HistoryParser {

    public List<HistoryExcangeMarket> runParse(String url) {
        var dateFromUrl = DailyParser.getDateFromUrl(url);
        var lines = DailyParser.parseLinesToList(url);
        var header = lines.get(0).split("\\|");
        return mapLinesToEntity(lines, getStringIntegerMap(header), url)
                .stream()
                .filter(Objects::nonNull).toList();
    }

    private static List<HistoryExcangeMarket> mapLinesToEntity(List<String> lines, Map<String, Integer> map, String urlAdress) {
        return lines.stream()
                .skip(1)
                .map(entity -> {
                    var split = entity.split("\\|");
                    var stringIntegerMap = getStringIntegerMap(split);
                    if (split[0].equals("Date")) {
                        map.clear();
                        map.putAll(stringIntegerMap);
                        return null;
                    }
                    if (map.size() == 33 || map.size() == 34 || map.size() == 35 || map.size() == 36) {
                        return getHistoryExcangeMarketEntity(urlAdress, map, split);
                    } else if (map.size() == 31) {
                        return getHistoryExcangeMarketEntityWithOutRubAndHrk(urlAdress, map, split);
                    } else if (map.size() == 32) {
                        return getHistoryExcangeMarketEntityIn2022WithOutRub(urlAdress, map, split);
                    } else return null;
                }).collect(toList());
    }

    @NotNull
    private static HistoryExcangeMarketEntity getHistoryExcangeMarketEntity(String urlAdress, Map<String, Integer> map, String[] split) {
        return new HistoryExcangeMarketEntity(
                LocalDate.parse(split[0], DateFormat.FORMATTER),

                convertToBigDecimal(split[1]).divide(convertToBigDecimal(map.getOrDefault("aud", 1))),
                convertToBigDecimal(split[2]).divide(convertToBigDecimal(map.getOrDefault("bgn", 1))),
                convertToBigDecimal(split[3]).divide(convertToBigDecimal(map.getOrDefault("brl", 1))),
                convertToBigDecimal(split[4]).divide(convertToBigDecimal(map.getOrDefault("cad", 1))),
                convertToBigDecimal(split[5]).divide(convertToBigDecimal(map.getOrDefault("chf", 1))),
                convertToBigDecimal(split[6]).divide(convertToBigDecimal(map.getOrDefault("cny", 1))),
                convertToBigDecimal(split[7]).divide(convertToBigDecimal(map.getOrDefault("dkk", 1))),
                convertToBigDecimal(split[8]).divide(convertToBigDecimal(map.getOrDefault("eur", 1))),
                convertToBigDecimal(split[9]).divide(convertToBigDecimal(map.getOrDefault("gbp", 1))),
                convertToBigDecimal(split[10]).divide(convertToBigDecimal(map.getOrDefault("hkd", 1))),

                convertToBigDecimal(split[11]).divide(convertToBigDecimal(map.getOrDefault("hrk", 1))),

                convertToBigDecimal(split[12]).divide(convertToBigDecimal(map.getOrDefault("huf", 1))),
                convertToBigDecimal(split[13]).divide(convertToBigDecimal(map.getOrDefault("idr", 1))),
                convertToBigDecimal(split[14]).divide(convertToBigDecimal(map.getOrDefault("ils", 1))),
                convertToBigDecimal(split[15]).divide(convertToBigDecimal(map.getOrDefault("inr", 1))),
                convertToBigDecimal(split[16]).divide(convertToBigDecimal(map.getOrDefault("isk", 1))),
                convertToBigDecimal(split[17]).divide(convertToBigDecimal(map.getOrDefault("jpy", 1))),
                convertToBigDecimal(split[18]).divide(convertToBigDecimal(map.getOrDefault("krw", 1))),
                convertToBigDecimal(split[19]).divide(convertToBigDecimal(map.getOrDefault("mxn", 1))),
                convertToBigDecimal(split[20]).divide(convertToBigDecimal(map.getOrDefault("myr", 1))),
                convertToBigDecimal(split[21]).divide(convertToBigDecimal(map.getOrDefault("nok", 1))),
                convertToBigDecimal(split[22]).divide(convertToBigDecimal(map.getOrDefault("nzd", 1))),
                convertToBigDecimal(split[23]).divide(convertToBigDecimal(map.getOrDefault("php", 1))),
                convertToBigDecimal(split[24]).divide(convertToBigDecimal(map.getOrDefault("pln", 1))),
                convertToBigDecimal(split[25]).divide(convertToBigDecimal(map.getOrDefault("ron", 1))),

                convertToBigDecimal(split[26]).divide(convertToBigDecimal(map.getOrDefault("rub", 1))),

                convertToBigDecimal(split[27]).divide(convertToBigDecimal(map.getOrDefault("sek", 1))),
                convertToBigDecimal(split[28]).divide(convertToBigDecimal(map.getOrDefault("sgd", 1))),
                convertToBigDecimal(split[29]).divide(convertToBigDecimal(map.getOrDefault("thb", 1))),
                convertToBigDecimal(split[30]).divide(convertToBigDecimal(map.getOrDefault("try", 1))),
                convertToBigDecimal(split[31]).divide(convertToBigDecimal(map.getOrDefault("usd", 1))),
                convertToBigDecimal(split[32]).divide(convertToBigDecimal(map.getOrDefault("xdr", 1))),
                convertToBigDecimal(split[33]).divide(convertToBigDecimal(map.getOrDefault("zar", 1))),

                DailyParser.getDateFromUrl(urlAdress),
                LocalDateTime.now()
        );
    }

    @NotNull
    private static HistoryExcangeMarketEntity getHistoryExcangeMarketEntityIn2022WithOutRub(String urlAdress, Map<String, Integer> map, String[] split) {
        return new HistoryExcangeMarketEntity(
                LocalDate.parse(split[0], DateFormat.FORMATTER),

                convertToBigDecimal(split[1]).divide(convertToBigDecimal(map.getOrDefault("aud", 1))),
                convertToBigDecimal(split[2]).divide(convertToBigDecimal(map.getOrDefault("bgn", 1))),
                convertToBigDecimal(split[3]).divide(convertToBigDecimal(map.getOrDefault("brl", 1))),
                convertToBigDecimal(split[4]).divide(convertToBigDecimal(map.getOrDefault("cad", 1))),
                convertToBigDecimal(split[5]).divide(convertToBigDecimal(map.getOrDefault("chf", 1))),
                convertToBigDecimal(split[6]).divide(convertToBigDecimal(map.getOrDefault("cny", 1))),
                convertToBigDecimal(split[7]).divide(convertToBigDecimal(map.getOrDefault("dkk", 1))),
                convertToBigDecimal(split[8]).divide(convertToBigDecimal(map.getOrDefault("eur", 1))),
                convertToBigDecimal(split[9]).divide(convertToBigDecimal(map.getOrDefault("gbp", 1))),
                convertToBigDecimal(split[10]).divide(convertToBigDecimal(map.getOrDefault("hkd", 1))),

                convertToBigDecimal(split[11]).divide(convertToBigDecimal(map.getOrDefault("hrk", 1))),

                convertToBigDecimal(split[12]).divide(convertToBigDecimal(map.getOrDefault("huf", 1))),
                convertToBigDecimal(split[13]).divide(convertToBigDecimal(map.getOrDefault("idr", 1))),
                convertToBigDecimal(split[14]).divide(convertToBigDecimal(map.getOrDefault("ils", 1))),
                convertToBigDecimal(split[15]).divide(convertToBigDecimal(map.getOrDefault("inr", 1))),
                convertToBigDecimal(split[16]).divide(convertToBigDecimal(map.getOrDefault("isk", 1))),
                convertToBigDecimal(split[17]).divide(convertToBigDecimal(map.getOrDefault("jpy", 1))),
                convertToBigDecimal(split[18]).divide(convertToBigDecimal(map.getOrDefault("krw", 1))),
                convertToBigDecimal(split[19]).divide(convertToBigDecimal(map.getOrDefault("mxn", 1))),
                convertToBigDecimal(split[20]).divide(convertToBigDecimal(map.getOrDefault("myr", 1))),
                convertToBigDecimal(split[21]).divide(convertToBigDecimal(map.getOrDefault("nok", 1))),
                convertToBigDecimal(split[22]).divide(convertToBigDecimal(map.getOrDefault("nzd", 1))),
                convertToBigDecimal(split[23]).divide(convertToBigDecimal(map.getOrDefault("php", 1))),
                convertToBigDecimal(split[24]).divide(convertToBigDecimal(map.getOrDefault("pln", 1))),
                convertToBigDecimal(split[25]).divide(convertToBigDecimal(map.getOrDefault("ron", 1))),
                null,
                convertToBigDecimal(split[26]).divide(convertToBigDecimal(map.getOrDefault("sek", 1))),
                convertToBigDecimal(split[27]).divide(convertToBigDecimal(map.getOrDefault("sgd", 1))),
                convertToBigDecimal(split[28]).divide(convertToBigDecimal(map.getOrDefault("thb", 1))),
                convertToBigDecimal(split[29]).divide(convertToBigDecimal(map.getOrDefault("try", 1))),
                convertToBigDecimal(split[30]).divide(convertToBigDecimal(map.getOrDefault("usd", 1))),
                convertToBigDecimal(split[31]).divide(convertToBigDecimal(map.getOrDefault("xdr", 1))),
                convertToBigDecimal(split[32]).divide(convertToBigDecimal(map.getOrDefault("zar", 1))),

                DailyParser.getDateFromUrl(urlAdress),
                LocalDateTime.now()
        );
    }

    @NotNull
    private static HistoryExcangeMarketEntityWithOutRubAndHrk getHistoryExcangeMarketEntityWithOutRubAndHrk(String urlAdress, Map<String, Integer> map, String[] split) {
        return new HistoryExcangeMarketEntityWithOutRubAndHrk(
                LocalDate.parse(split[0], DateFormat.FORMATTER),
                convertToBigDecimal(split[1]).divide(convertToBigDecimal(map.getOrDefault("aud", 1))),
                convertToBigDecimal(split[2]).divide(convertToBigDecimal(map.getOrDefault("bgn", 1))),
                convertToBigDecimal(split[3]).divide(convertToBigDecimal(map.getOrDefault("brl", 1))),
                convertToBigDecimal(split[4]).divide(convertToBigDecimal(map.getOrDefault("cad", 1))),
                convertToBigDecimal(split[5]).divide(convertToBigDecimal(map.getOrDefault("chf", 1))),
                convertToBigDecimal(split[6]).divide(convertToBigDecimal(map.getOrDefault("cny", 1))),
                convertToBigDecimal(split[7]).divide(convertToBigDecimal(map.getOrDefault("dkk", 1))),
                convertToBigDecimal(split[8]).divide(convertToBigDecimal(map.getOrDefault("eur", 1))),
                convertToBigDecimal(split[9]).divide(convertToBigDecimal(map.getOrDefault("gbp", 1))),
                convertToBigDecimal(split[10]).divide(convertToBigDecimal(map.getOrDefault("hkd", 1))),


                convertToBigDecimal(split[11]).divide(convertToBigDecimal(map.getOrDefault("huf", 1))),
                convertToBigDecimal(split[12]).divide(convertToBigDecimal(map.getOrDefault("idr", 1))),
                convertToBigDecimal(split[13]).divide(convertToBigDecimal(map.getOrDefault("ils", 1))),
                convertToBigDecimal(split[14]).divide(convertToBigDecimal(map.getOrDefault("inr", 1))),
                convertToBigDecimal(split[15]).divide(convertToBigDecimal(map.getOrDefault("isk", 1))),
                convertToBigDecimal(split[16]).divide(convertToBigDecimal(map.getOrDefault("jpy", 1))),
                convertToBigDecimal(split[17]).divide(convertToBigDecimal(map.getOrDefault("krw", 1))),
                convertToBigDecimal(split[18]).divide(convertToBigDecimal(map.getOrDefault("mxn", 1))),
                convertToBigDecimal(split[19]).divide(convertToBigDecimal(map.getOrDefault("myr", 1))),
                convertToBigDecimal(split[20]).divide(convertToBigDecimal(map.getOrDefault("nok", 1))),
                convertToBigDecimal(split[21]).divide(convertToBigDecimal(map.getOrDefault("nzd", 1))),
                convertToBigDecimal(split[22]).divide(convertToBigDecimal(map.getOrDefault("php", 1))),
                convertToBigDecimal(split[23]).divide(convertToBigDecimal(map.getOrDefault("pln", 1))),
                convertToBigDecimal(split[24]).divide(convertToBigDecimal(map.getOrDefault("ron", 1))),


                convertToBigDecimal(split[25]).divide(convertToBigDecimal(map.getOrDefault("sek", 1))),
                convertToBigDecimal(split[26]).divide(convertToBigDecimal(map.getOrDefault("sgd", 1))),
                convertToBigDecimal(split[27]).divide(convertToBigDecimal(map.getOrDefault("thb", 1))),
                convertToBigDecimal(split[28]).divide(convertToBigDecimal(map.getOrDefault("try", 1))),
                convertToBigDecimal(split[29]).divide(convertToBigDecimal(map.getOrDefault("usd", 1))),
                convertToBigDecimal(split[30]).divide(convertToBigDecimal(map.getOrDefault("xdr", 1))),
                convertToBigDecimal(split[31]).divide(convertToBigDecimal(map.getOrDefault("zar", 1))),

                DailyParser.getDateFromUrl(urlAdress),
                LocalDateTime.now()
        );
    }

    private static BigDecimal convertToBigDecimal(Integer integer) {
        return new BigDecimal(integer);
    }

    private static BigDecimal convertToBigDecimal(String string) {
        return new BigDecimal(string);
    }

    @NotNull
    private static Map<String, Integer> getStringIntegerMap(String[] header) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String elem : header) {
            var elems = elem.split(" ");
            if (elems.length == 1) {
                continue;
            } else {
                map.put(elems[1].toLowerCase(), Integer.parseInt(elems[0]));
            }
        }
        return map;
    }


}
