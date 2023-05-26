package ru.lebedev.cronachecker.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lebedev.cronachecker.dao.DaoHistoryImpl;

@Service
@Getter
public class InsertHistoryService {
    private final InsertService insertService;
    @Value("${http.url.history}")
    private String url;

    @Autowired
    public InsertHistoryService(InsertService insertService) {
        this.insertService = insertService;
    }

    public void insertHistory(DaoHistoryImpl daoHistory, String url, String... years) {
        for (String year : years) {
            insertService.insertHistoryExcangeMarket(daoHistory, url + year);
        }
    }

}
