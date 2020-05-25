package com.academiaspring.demo.parser;

import com.academiaspring.demo.enums.Currency;
import com.academiaspring.demo.models.Rate;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RateParser {
    public List<Rate> converntToListRate(JsonNode jsonNode[]) {
        List<Rate> rateList = new ArrayList<>();
        try {
            int index = 0;
            while (true) {
                Rate rate = new Rate();
                assert jsonNode != null;
                JsonNode rateElement = jsonNode[0].get("rates").get(index);
                rate.setCurrency(rateElement.get("currency").asText());
                rate.setCode(Currency.valueOf(rateElement.get("code").asText()));
                rate.setMid(rateElement.get("mid").asDouble());
                rateList.add(rate);
                index++;
            }
        } finally {
            return rateList;
        }
    }
}
