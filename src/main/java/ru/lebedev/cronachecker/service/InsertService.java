package ru.lebedev.cronachecker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.lebedev.cronachecker.dao.DaoImpl;
import ru.lebedev.cronachecker.util.DateFormat;
import ru.lebedev.cronachecker.util.Parser;

import java.time.LocalDate;

@Service
public class InsertService {
    @Value("${http.url}")
    private String url;

    private final String currentDay = LocalDate.of(2019,2,28).format(DateFormat.FORMATTER);

    @Bean
    public CommandLineRunner commandLineRunner(DaoImpl dao) {
        return runner -> {
            insertExcangeMarket(dao, url+currentDay);
        };
    }

    private void insertExcangeMarket(DaoImpl dao, String url) {
        var exchangeMarketEntities = Parser.runParse(url);
        exchangeMarketEntities.forEach(dao::save);
    }

}
