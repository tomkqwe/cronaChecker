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

import static java.util.stream.Collectors.toList;

@UtilityClass
public class HistoryParser {

    public List<HistoryExcangeMarket> runParse(String url) {
        var dateFromUrl = DailyParser.getDateFromUrl(url);
        var lines = DailyParser.parseLinesToList(url);
        var header = lines.get(0).split("\\|");
        return mapLinesToEntity(lines, getStringIntegerMap(header), url);

    }

    private static List<HistoryExcangeMarket> mapLinesToEntity(List<String> lines, Map<String, Integer> map, String urlAdress) {
        return lines.stream()
                .skip(1)
                .map(entity -> {
                    var split = entity.split("\\|");
                    if (map.size() == 33) {
                        return getHistoryExcangeMarketEntity(urlAdress, map, split);
                    } else {
                        return getHistoryExcangeMarketEntityWithOutRubAndHrk(urlAdress, map, split);
                    }
                }).collect(toList());
    }


    @NotNull
    private static HistoryExcangeMarketEntity getHistoryExcangeMarketEntity(String urlAdress, Map<String, Integer> map, String[] split) {
        return new HistoryExcangeMarketEntity(
                LocalDate.parse(split[0], DateFormat.FORMATTER),

                convertToBigDecimal(split[1]).divide(convertToBigDecimal(map.get("aud"))),
                convertToBigDecimal(split[2]).divide(convertToBigDecimal(map.get("bgn"))),
                convertToBigDecimal(split[3]).divide(convertToBigDecimal(map.get("brl"))),
                convertToBigDecimal(split[4]).divide(convertToBigDecimal(map.get("cad"))),
                convertToBigDecimal(split[5]).divide(convertToBigDecimal(map.get("chf"))),
                convertToBigDecimal(split[6]).divide(convertToBigDecimal(map.get("cny"))),
                convertToBigDecimal(split[7]).divide(convertToBigDecimal(map.get("dkk"))),
                convertToBigDecimal(split[8]).divide(convertToBigDecimal(map.get("eur"))),
                convertToBigDecimal(split[9]).divide(convertToBigDecimal(map.get("gbp"))),
                convertToBigDecimal(split[10]).divide(convertToBigDecimal(map.get("hkd"))),

                convertToBigDecimal(split[11]).divide(convertToBigDecimal(map.get("hrk"))),

                convertToBigDecimal(split[12]).divide(convertToBigDecimal(map.get("huf"))),
                convertToBigDecimal(split[13]).divide(convertToBigDecimal(map.get("idr"))),
                convertToBigDecimal(split[14]).divide(convertToBigDecimal(map.get("ils"))),
                convertToBigDecimal(split[15]).divide(convertToBigDecimal(map.get("inr"))),
                convertToBigDecimal(split[16]).divide(convertToBigDecimal(map.get("isk"))),
                convertToBigDecimal(split[17]).divide(convertToBigDecimal(map.get("jpy"))),
                convertToBigDecimal(split[18]).divide(convertToBigDecimal(map.get("krw"))),
                convertToBigDecimal(split[19]).divide(convertToBigDecimal(map.get("mxn"))),
                convertToBigDecimal(split[20]).divide(convertToBigDecimal(map.get("myr"))),
                convertToBigDecimal(split[21]).divide(convertToBigDecimal(map.get("nok"))),
                convertToBigDecimal(split[22]).divide(convertToBigDecimal(map.get("nzd"))),
                convertToBigDecimal(split[23]).divide(convertToBigDecimal(map.get("php"))),
                convertToBigDecimal(split[24]).divide(convertToBigDecimal(map.get("pln"))),
                convertToBigDecimal(split[25]).divide(convertToBigDecimal(map.get("ron"))),

                convertToBigDecimal(split[26]).divide(convertToBigDecimal(map.get("rub"))),

                convertToBigDecimal(split[27]).divide(convertToBigDecimal(map.get("sek"))),
                convertToBigDecimal(split[28]).divide(convertToBigDecimal(map.get("sgd"))),
                convertToBigDecimal(split[29]).divide(convertToBigDecimal(map.get("thb"))),
                convertToBigDecimal(split[30]).divide(convertToBigDecimal(map.get("try"))),
                convertToBigDecimal(split[31]).divide(convertToBigDecimal(map.get("usd"))),
                convertToBigDecimal(split[32]).divide(convertToBigDecimal(map.get("xdr"))),
                convertToBigDecimal(split[33]).divide(convertToBigDecimal(map.get("zar"))),

                DailyParser.getDateFromUrl(urlAdress),
                LocalDateTime.now()
        );
    }

    @NotNull
    private static HistoryExcangeMarketEntityWithOutRubAndHrk getHistoryExcangeMarketEntityWithOutRubAndHrk(String urlAdress, Map<String, Integer> map, String[] split) {
        return new HistoryExcangeMarketEntityWithOutRubAndHrk(
                LocalDate.parse(split[0], DateFormat.FORMATTER),
                convertToBigDecimal(split[1]).divide(convertToBigDecimal(map.get("aud"))),
                convertToBigDecimal(split[2]).divide(convertToBigDecimal(map.get("bgn"))),
                convertToBigDecimal(split[3]).divide(convertToBigDecimal(map.get("brl"))),
                convertToBigDecimal(split[4]).divide(convertToBigDecimal(map.get("cad"))),
                convertToBigDecimal(split[5]).divide(convertToBigDecimal(map.get("chf"))),
                convertToBigDecimal(split[6]).divide(convertToBigDecimal(map.get("cny"))),
                convertToBigDecimal(split[7]).divide(convertToBigDecimal(map.get("dkk"))),
                convertToBigDecimal(split[8]).divide(convertToBigDecimal(map.get("eur"))),
                convertToBigDecimal(split[9]).divide(convertToBigDecimal(map.get("gbp"))),
                convertToBigDecimal(split[10]).divide(convertToBigDecimal(map.get("hkd"))),


                convertToBigDecimal(split[11]).divide(convertToBigDecimal(map.get("huf"))),
                convertToBigDecimal(split[12]).divide(convertToBigDecimal(map.get("idr"))),
                convertToBigDecimal(split[13]).divide(convertToBigDecimal(map.get("ils"))),
                convertToBigDecimal(split[14]).divide(convertToBigDecimal(map.get("inr"))),
                convertToBigDecimal(split[15]).divide(convertToBigDecimal(map.get("isk"))),
                convertToBigDecimal(split[16]).divide(convertToBigDecimal(map.get("jpy"))),
                convertToBigDecimal(split[17]).divide(convertToBigDecimal(map.get("krw"))),
                convertToBigDecimal(split[18]).divide(convertToBigDecimal(map.get("mxn"))),
                convertToBigDecimal(split[19]).divide(convertToBigDecimal(map.get("myr"))),
                convertToBigDecimal(split[20]).divide(convertToBigDecimal(map.get("nok"))),
                convertToBigDecimal(split[21]).divide(convertToBigDecimal(map.get("nzd"))),
                convertToBigDecimal(split[22]).divide(convertToBigDecimal(map.get("php"))),
                convertToBigDecimal(split[23]).divide(convertToBigDecimal(map.get("pln"))),
                convertToBigDecimal(split[24]).divide(convertToBigDecimal(map.get("ron"))),


                convertToBigDecimal(split[25]).divide(convertToBigDecimal(map.get("sek"))),
                convertToBigDecimal(split[26]).divide(convertToBigDecimal(map.get("sgd"))),
                convertToBigDecimal(split[27]).divide(convertToBigDecimal(map.get("thb"))),
                convertToBigDecimal(split[28]).divide(convertToBigDecimal(map.get("try"))),
                convertToBigDecimal(split[29]).divide(convertToBigDecimal(map.get("usd"))),
                convertToBigDecimal(split[30]).divide(convertToBigDecimal(map.get("xdr"))),
                convertToBigDecimal(split[31]).divide(convertToBigDecimal(map.get("zar"))),

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
