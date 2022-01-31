package com.example.demo.model.service;

import com.example.demo.entity.TargetUrl;
import com.example.demo.model.dataexceptons.UrlNotFoundException;
import com.example.demo.repo.TargetUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    @Autowired
    private TargetUrlRepository targetUrlRepository;

    public List<TargetUrl> allUrlList() {
        return targetUrlRepository.findAll();
    }

    public TargetUrl findById(Long id) {
        Optional<TargetUrl> targetUrl = targetUrlRepository.findById(id);
        return targetUrl.orElseThrow(() -> new UrlNotFoundException(id));
    }

    public TargetUrl findByName(String name) {
        return targetUrlRepository.findByName(name);
    }

    public void deleteById(Long id) {
        targetUrlRepository.deleteById(id);
    }
}
