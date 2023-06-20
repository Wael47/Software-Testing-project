package com.qa.proj1.repository;

import com.qa.proj1.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICurrencyRepository extends JpaRepository<Currency, Long> {

}