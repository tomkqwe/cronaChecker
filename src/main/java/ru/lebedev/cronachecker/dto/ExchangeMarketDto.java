package ru.lebedev.cronachecker.dto;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import lombok.*;

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
