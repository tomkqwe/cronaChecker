package ru.lebedev.cronachecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lebedev.cronachecker.dao.DaoExchangeMarketImpl;
import ru.lebedev.cronachecker.dao.DaoHistoryImpl;
import ru.lebedev.cronachecker.dto.ExchangeMarketDto;
import ru.lebedev.cronachecker.service.AggregationService;
import ru.lebedev.cronachecker.service.InsertHistoryService;
import ru.lebedev.cronachecker.service.InsertPeriodService;

import java.util.List;

@RestController
public class Controller {

    private final AggregationService aggregationService;
    private final InsertPeriodService periodService;
    private final DaoExchangeMarketImpl dao;

    private final DaoHistoryImpl daoHistory;
    private final InsertHistoryService historyService;

    @Autowired
    public Controller(AggregationService aggregationService, InsertPeriodService periodService, DaoExchangeMarketImpl dao, DaoHistoryImpl daoHistory, InsertHistoryService historyService) {
        this.aggregationService = aggregationService;
        this.periodService = periodService;
        this.dao = dao;
        this.daoHistory = daoHistory;
        this.historyService = historyService;
    }

    @GetMapping("/getStatistic")
    List<ExchangeMarketDto> aggregationMapping(@RequestParam String... codes) {
        return aggregationService.selectMinMaxAvgValue(codes);
    }

    @GetMapping("/insertPeriod")
    String insertPeriodMapping(@RequestParam String startDate, @RequestParam String endDate) {
        var url = periodService.getUrl();
        try {
            periodService.insertPeriod(dao, startDate, endDate);
        } catch (Exception e) {
            return "Insert Failed";
        }
        return "Insert Successful";
    }

    @GetMapping("/insertHistory")
    String insertHistoryMapping(@RequestParam String... years) {
        var url = historyService.getUrl();
        try {
            historyService.insertHistory(daoHistory, url, years);
        } catch (Exception e) {
            e.printStackTrace();
            return "Insert Failed " + e.getMessage();
        }
        return "Insert Successful";
    }

    @GetMapping("/getHistoryStatistic")
    List<ExchangeMarketDto> getHistoryStatistic(@RequestParam String... currencies) {
        return aggregationService.selectHistoryMinMaxAvgValue(currencies);
    }
}
