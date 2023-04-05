package ru.lebedev.cronachecker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "crona_repository",schema = "exchange_market")
public class ExchangeMarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "country")
    String country;
    @Column(name = "currency")
    String currency;
    @Column(name = "amount")
    Integer amount;
    @Column(name = "code")
    String code;
    @Column(name = "rate")
    BigDecimal rate;

    public ExchangeMarketEntity() {
    }

    public ExchangeMarketEntity(String country, String currency, Integer amount, String code, BigDecimal rate) {
        this.country = country;
        this.currency = currency;
        this.amount = amount;
        this.code = code;
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExchangeMarketEntity that = (ExchangeMarketEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
