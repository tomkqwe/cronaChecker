package ru.lebedev.cronachecker.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class ExchangeMarketDto {
    private String code;
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal avg;

    public ExchangeMarketDto(String code, BigDecimal min, BigDecimal max, BigDecimal avg) {
        this.code = code;
        this.min = min;
        this.max = max;
        this.avg = avg;
    }
}
