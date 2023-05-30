package ru.lebedev.cronachecker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lebedev.cronachecker.dao.DaoExchangeMarketImpl;
import ru.lebedev.cronachecker.dao.DaoHistoryImpl;
import ru.lebedev.cronachecker.util.DailyParser;
import ru.lebedev.cronachecker.util.HistoryParser;

@Service
public class InsertService {
    public void insertExcangeMarket(DaoExchangeMarketImpl dao, @Value("${http.url.daily}") String url) {
        var exchangeMarketEntities = DailyParser.runParse(url);
        exchangeMarketEntities.forEach(dao::save);
    }

    public void insertHistoryExcangeMarket(DaoHistoryImpl dao, @Value("${http.url.history}") String url) {
        var historyExcangeMarkets = HistoryParser.runParse(url);
        historyExcangeMarkets.forEach(dao::save);
    }


}
