package com.example.demo.model.dataexceptons;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(Long id) {
        super("Could not find Url in database by id [ " + id + " ]");
    }
}
