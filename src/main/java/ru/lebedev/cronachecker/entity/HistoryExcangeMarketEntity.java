package ru.lebedev.cronachecker.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@Setter
@ToString
@Entity
@Table(name = "history_crona_repository", schema = "exchange_market")
public class HistoryExcangeMarketEntity implements HistoryExcangeMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "currency_date")
    private LocalDate currencyDate;

    @Column(name = "aud")
    private BigDecimal aud;
    @Column(name = "bgn")
    private BigDecimal bgn;
    @Column(name = "brl")
    private BigDecimal brl;
    @Column(name = "cad")
    private BigDecimal cad;
    @Column(name = "chf")
    private BigDecimal chf;
    @Column(name = "cny")
    private BigDecimal cny;
    @Column(name = "dkk")
    private BigDecimal dkk;
    @Column(name = "eur")
    private BigDecimal eur;
    @Column(name = "gbp")
    private BigDecimal gbp;
    @Column(name = "hkd")
    private BigDecimal hkd;
    @Column(name = "hrk")
    private BigDecimal hrk;
    @Column(name = "huf")
    private BigDecimal huf;
    @Column(name = "idr")
    private BigDecimal idr;
    @Column(name = "ils")
    private BigDecimal ils;
    @Column(name = "inr")
    private BigDecimal inr;
    @Column(name = "isk")
    private BigDecimal isk;
    @Column(name = "jpy")
    private BigDecimal jpy;
    @Column(name = "krw")
    private BigDecimal krw;
    @Column(name = "mxn")
    private BigDecimal mxn;
    @Column(name = "myr")
    private BigDecimal myr;
    @Column(name = "nok")
    private BigDecimal nok;
    @Column(name = "nzd")
    private BigDecimal nzd;
    @Column(name = "php")
    private BigDecimal php;
    @Column(name = "pln")
    private BigDecimal pln;
    @Column(name = "ron")
    private BigDecimal ron;
    @Column(name = "rub")
    private BigDecimal rub;
    @Column(name = "sek")
    private BigDecimal sek;
    @Column(name = "sgd")
    private BigDecimal sgd;
    @Column(name = "thb")
    private BigDecimal thb;
    @Column(name = "try")
    private BigDecimal tRy;
    @Column(name = "usd")
    private BigDecimal usd;
    @Column(name = "xdr")
    private BigDecimal xdr;
    @Column(name = "zar")
    private BigDecimal zar;

    @Column(name = "currency_year")
    private String currencyYear;
    @Column(name = "insert_timestamp")
    private LocalDateTime insertTimestamp;


    public HistoryExcangeMarketEntity() {
    }

    public HistoryExcangeMarketEntity(LocalDate currencyDate, BigDecimal aud, BigDecimal bgn, BigDecimal brl, BigDecimal cad, BigDecimal chf, BigDecimal cny, BigDecimal dkk, BigDecimal eur, BigDecimal gbp, BigDecimal hkd, BigDecimal hrk, BigDecimal huf, BigDecimal idr, BigDecimal ils, BigDecimal inr, BigDecimal isk, BigDecimal jpy, BigDecimal krw, BigDecimal mxn, BigDecimal myr, BigDecimal nok, BigDecimal nzd, BigDecimal php, BigDecimal pln, BigDecimal ron, BigDecimal rub, BigDecimal sek, BigDecimal sgd, BigDecimal thb, BigDecimal tRy, BigDecimal usd, BigDecimal xdr, BigDecimal zar, String currencyYear, LocalDateTime insertTimestamp) {
        this.currencyDate = currencyDate;
        this.aud = aud;
        this.bgn = bgn;
        this.brl = brl;
        this.cad = cad;
        this.chf = chf;
        this.cny = cny;
        this.dkk = dkk;
        this.eur = eur;
        this.gbp = gbp;
        this.hkd = hkd;
        this.hrk = hrk;
        this.huf = huf;
        this.idr = idr;
        this.ils = ils;
        this.inr = inr;
        this.isk = isk;
        this.jpy = jpy;
        this.krw = krw;
        this.mxn = mxn;
        this.myr = myr;
        this.nok = nok;
        this.nzd = nzd;
        this.php = php;
        this.pln = pln;
        this.ron = ron;
        this.rub = rub;
        this.sek = sek;
        this.sgd = sgd;
        this.thb = thb;
        this.tRy = tRy;
        this.usd = usd;
        this.xdr = xdr;
        this.zar = zar;
        this.currencyYear = currencyYear;
        this.insertTimestamp = insertTimestamp;
    }
}
