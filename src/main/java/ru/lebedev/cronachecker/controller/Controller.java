package ru.lebedev.cronachecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lebedev.cronachecker.dto.ExchangeMarketDto;
import ru.lebedev.cronachecker.service.AggregationService;

import java.util.List;

@RestController
public class Controller {

    private final AggregationService aggregationService;

    @Autowired
    public Controller(AggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    @GetMapping("/hello")
    List<ExchangeMarketDto> firstMapping(@RequestParam String... codes) {
        return aggregationService.selectMinMaxAvgValue(codes);
    }
}
