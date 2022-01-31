package com.example.demo.controller;

import com.example.demo.model.service.CounterService;
import com.example.demo.model.service.WebClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class URLController {

    @Autowired
    private WebClientService webClientService;

    @Autowired
    private CounterService counterService;

    @Operation(summary = "Performs word count",
                description = "Performs word count on the web-page and saves result to the database")
    @PostMapping("/target_url")
    public Map<String, Integer> getWordsCount(@RequestBody
                                                  @Parameter(name = "newUrl",
                                                          description = "Action will be performed on this URL",
                                                          required = true,
                                                                example = "https://www.simbirsoft.com/")
                                                                String newUrl) {
        String rawPageText = webClientService.getTextFromRequestBody(newUrl);
        return counterService.count(rawPageText, newUrl);
    }
}
