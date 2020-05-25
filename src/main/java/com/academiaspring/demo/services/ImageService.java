package com.academiaspring.demo.services;

import com.academiaspring.demo.models.Image;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageService {
    private RestTemplate restTemplate = new RestTemplate();

    public Image getCatPhoto() {
        JsonNode[] jsonNode = restTemplate.getForObject("https://api.thecatapi.com/v1/images/search", JsonNode[].class);
        Image image = new Image(jsonNode[0].get("url").asText());
        return image;
    }
}
