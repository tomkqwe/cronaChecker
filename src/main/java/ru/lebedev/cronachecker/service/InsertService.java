package ru.lebedev.cronachecker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lebedev.cronachecker.dao.DaoImpl;
import ru.lebedev.cronachecker.util.DateFormat;
import ru.lebedev.cronachecker.util.Parser;

import java.time.LocalDate;

@Service
public class InsertService {
    @Value("${http.url}")
    private String url;

    public void insertExcangeMarket(DaoImpl dao, String url) {
        var exchangeMarketEntities = Parser.runParse(url);
        exchangeMarketEntities.forEach(dao::save);
    }

}
