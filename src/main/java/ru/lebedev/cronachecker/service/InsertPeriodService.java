package ru.lebedev.cronachecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.lebedev.cronachecker.dao.DaoImpl;
import ru.lebedev.cronachecker.util.DatesUtil;

@Service
public class InsertPeriodService {
    private final InsertService insertService;
    @Value("${http.url}")
    private String url;

    @Autowired
    public InsertPeriodService(InsertService insertService) {
        this.insertService = insertService;
    }

    @Bean
    public CommandLineRunner commandLineRunner(DaoImpl dao, @Value("${start.date}")String startDate,@Value("${end.date}") String endDate) {
        return runner -> {
            insertPeriod(dao, startDate, endDate);
        };
    }

    private void insertPeriod(DaoImpl dao, String startDate, String endDate) {
        var betweenTwoDates = DatesUtil.daysBetweenTwoDates(startDate, endDate);
        while (betweenTwoDates > 0) {
            insertService.insertExcangeMarket(dao, url + startDate);
            betweenTwoDates--;
            startDate = DatesUtil.incrementDay(startDate);
        }

    }
}
