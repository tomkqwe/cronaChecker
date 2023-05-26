package ru.lebedev.cronachecker.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lebedev.cronachecker.dao.DaoExchangeMarketImpl;
import ru.lebedev.cronachecker.util.DatesUtil;

@Service
@Getter
public class InsertPeriodService {
    private final InsertService insertService;
    @Autowired
    DaoExchangeMarketImpl dao;
    @Value("${http.url.daily}")
    private String url;

    @Autowired
    public InsertPeriodService(InsertService insertService) {
        this.insertService = insertService;
    }

    public void insertPeriod(DaoExchangeMarketImpl dao, String startDate, String endDate) {
        var betweenTwoDates = DatesUtil.daysBetweenTwoDates(startDate, endDate);
        while (betweenTwoDates > 0) {
            insertService.insertExcangeMarket(dao, url + startDate);
            betweenTwoDates--;
            startDate = DatesUtil.incrementDay(startDate);
        }

    }
}
