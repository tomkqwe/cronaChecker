package ru.lebedev.cronachecker.service;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.cronachecker.dto.ExchangeMarketDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AggregationService {
    private final SessionFactory sessionFactory;

    @Transactional
    public List<ExchangeMarketDto> selectMinMaxAvgValue(String... codes) {
        List<ExchangeMarketDto> list = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            var stringBuilder = new StringBuilder();
            var hql = "select exme.code,min(exme.amount*exme.rate),max(exme.amount*exme.rate),avg(exme.amount*exme.rate) from ExchangeMarketEntity as exme group by exme.code";
            if (codes.length > 0) {
                hql = editHqlAddWhere(hql, codes);
            }
            var reesultHql = stringBuilder.append(hql).toString();
            var list1 = session.createQuery(reesultHql).stream().toList();
            for (Object o : list1) {
                var o1 = (Object[]) o;
                var exchangeMarketDto = new ExchangeMarketDto(((String) o1[0]), ((BigDecimal) o1[1]), (BigDecimal) o1[2], (BigDecimal.valueOf((double) o1[3])));
                list.add(exchangeMarketDto);
            }
        }
        return list;
    }

    @Transactional
    public List<ExchangeMarketDto> selectHistoryMinMaxAvgValue(String... currencies) {
        var exchangeMarketDtos = new ArrayList<ExchangeMarketDto>();
        var hql = "select '%s' as code,min(%s),max(%s),avg(%s) from exchange_market.history_crona_repository";
        try (var session = sessionFactory.openSession()) {
            for (String currency : currencies) {
                var finalHql = String.format(hql, currency, currency, currency, currency);
                var list = session.createNativeQuery(finalHql).stream().toList();
                var raw = ((Object[]) list.get(0));
                exchangeMarketDtos.add(new ExchangeMarketDto(((String) raw[0]), ((BigDecimal) raw[1]), ((BigDecimal) raw[2]), (BigDecimal) raw[3]));
            }

        }
        return exchangeMarketDtos;
    }

    public static String editHqlAddWhere(String hql, String... codes) {
        var builder = new StringBuilder();
        var groupBy = "group by exme.code";
        var replace1 = hql.replace(groupBy, "");
        builder.append(replace1);
        if (codes.length > 0) {
            builder.append("where ");
            for (String code : codes) {
                builder.append("exme.code = ").append("'").append(code.toUpperCase()).append("'").append(" or ");
            }
            builder.replace(builder.length() - 3, builder.length(), "");
        }
        builder.append(groupBy).append("\n");
        return builder.toString();
    }


}
