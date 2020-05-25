package com.academiaspring.demo.config;

import com.academiaspring.demo.models.Rate;
import com.academiaspring.demo.models.User;
import com.academiaspring.demo.parser.RateParser;
import com.academiaspring.demo.services.EmailService;
import com.academiaspring.demo.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    private final RateParser rateParser;
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public ScheduleConfig(RateParser rateParser, EmailService emailService, UserService userService) {
        this.rateParser = rateParser;
        this.emailService = emailService;
        this.userService = userService;
    }

    @Scheduled(fixedRate = 10000)
    public void scheduleNewsletter() {
        RestTemplate restTemplate = new RestTemplate();
        JsonNode[] jsonNode = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/A/", JsonNode[].class);
        List<Rate> rateList = rateParser.converntToListRate(jsonNode);
        Iterable<User> userIterable = userService.findAll();

        userIterable.forEach(user -> {
            Rate rate = (Rate) rateList
                    .stream()
                    .filter(filterRate -> filterRate.getCode() == user.getCurrency())
                    .toArray()[0];
            String subject = "Hi " + user.getFirstName() + " " + user.getLastName();
            emailService.sendSimpleMessage(user.getEmail(), subject, rate.toString());
        });

    }
}
