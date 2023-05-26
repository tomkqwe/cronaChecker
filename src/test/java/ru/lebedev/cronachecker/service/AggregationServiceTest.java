package ru.lebedev.cronachecker.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AggregationServiceTest {
    @Test
    void checkEditHqlAddWhereWithThreeArguments() {
        var hql = "select exme.code,min(exme.amount*exme.rate),max(exme.amount*exme.rate),avg(exme.amount*exme.rate) from ExchangeMarketEntity as exme group by exme.code";

        var actual = AggregationService.editHqlAddWhere(hql, "EUR", "usd", "Brl");

        var expected = """
                select exme.code,min(exme.amount*exme.rate),max(exme.amount*exme.rate),avg(exme.amount*exme.rate) from ExchangeMarketEntity as exme where exme.code = 'EUR' or exme.code = 'USD' or exme.code = 'BRL' group by exme.code
                """;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkEditHqlAddWhereWithOutArguments() {
        var hql = "select exme.code,min(exme.amount*exme.rate),max(exme.amount*exme.rate),avg(exme.amount*exme.rate) from ExchangeMarketEntity as exme group by exme.code";
        var actual = AggregationService.editHqlAddWhere(hql);

        var expected = hql + "\n";

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkEditHqlAddWhereWithOneArgument() {
        var hql = "select exme.code,min(exme.amount*exme.rate),max(exme.amount*exme.rate),avg(exme.amount*exme.rate) from ExchangeMarketEntity as exme group by exme.code";
        var actual = AggregationService.editHqlAddWhere(hql, "usd");

        var tmp = "select exme.code,min(exme.amount*exme.rate),max(exme.amount*exme.rate),avg(exme.amount*exme.rate) from ExchangeMarketEntity as exme where exme.code = 'USD' group by exme.code";
        var expected = tmp + "\n";
        Assertions.assertThat(actual).isEqualTo(expected);


    }
}