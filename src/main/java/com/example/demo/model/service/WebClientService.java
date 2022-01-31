package com.example.demo.model.service;

import io.netty.channel.ChannelOption;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Service
public class WebClientService {

    private WebClient createWebClient() {
        return WebClient
                .builder()
                .clientConnector(
                        new ReactorClientHttpConnector(
                                HttpClient.create()
                                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
                        )
                )
                .build();
    }

    public String getTextFromRequestBody(String newUrl) {

        WebClient client = createWebClient();

        String requestBodyText = client.get().uri(newUrl).retrieve().bodyToMono(String.class).block();

        try {
            if (requestBodyText.trim().isBlank())
                requestBodyText = "PageIsBlank";
        } catch (NullPointerException ex) {
            requestBodyText = "PageIsNull";
        }

        return requestBodyText;
    }
}
