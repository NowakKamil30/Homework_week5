package com.academiaspring.demo.Controller;

import com.academiaspring.demo.enums.Currency;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @GetMapping()
    public Currency[] getAllCurrency() {
        return Currency.values();
    }
}
