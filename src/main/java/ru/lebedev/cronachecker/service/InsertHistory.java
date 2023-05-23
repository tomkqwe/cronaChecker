package ru.lebedev.cronachecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.lebedev.cronachecker.dao.DaoHistoryImpl;

@Service
public class InsertHistory {
    private final InsertService insertService;

    @Autowired
    public InsertHistory(InsertService insertService) {
        this.insertService = insertService;
    }

    @Bean
    public ApplicationRunner applicationRunner(DaoHistoryImpl daoHistory, @Value("${http.url.history}") String url, @Value("${year}") String year) {
        return runner -> {
            insertService.insertHistoryExcangeMarket(daoHistory, url + year);
        };
    }


}
