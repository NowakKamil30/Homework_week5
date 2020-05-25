package com.academiaspring.demo.models;

import com.academiaspring.demo.enums.Currency;
import lombok.*;

import java.math.BigDecimal;

@Data
public class Rate {
    private String currency;
    private Currency code;
    private Double mid;
}
