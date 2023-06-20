package com.qa.proj1.repository;

import com.qa.proj1.entity.ConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IConversionRateRepository extends JpaRepository<ConversionRate, Long> {
    @Query("SELECT cr.rate FROM conversion_rate cr " +
            "JOIN currency_names fromCurrency ON cr.fromCurrency = fromCurrency.id " +
            "JOIN currency_names toCurrency ON cr.toCurrency = toCurrency.id " +
            "WHERE fromCurrency.currencyCode = :from AND toCurrency.currencyCode = :to")
    double getConversionRate(@Param("from") String from, @Param("to") String to);



}
