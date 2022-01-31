package com.example.demo.model.service;

import com.example.demo.entity.TargetUrl;
import com.example.demo.entity.Words;
import com.example.demo.repo.TargetUrlRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Service
public class CounterService {

    private final String DELIMITER = " -\"\t\n\r,.!?:;)(][1234567890»«/—©'%";

    @Autowired
    private TargetUrlRepository targetUrlRepository;

    public Map<String, Integer> count(String rawPageText, String newUrl){
        Map<String, Integer> wordsCount = new HashMap<>();

        String pageText = htmlTagRemove(rawPageText);
        List<String> stringList = splitString(pageText);
        fillMap(wordsCount, stringList);

        TargetUrl targetUrl = targetUrlRepository.findByName(newUrl);

        if(targetUrl == null){
            targetUrl = new TargetUrl();
            targetUrl.setName(newUrl);
        }

        List<Words> words = new ArrayList<>();
        if(!wordsCount.isEmpty()){
            for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
                words.add(new Words(entry.getKey(), entry.getValue()));
            }
        }
        targetUrl.setListWords(words);

        targetUrlRepository.save(targetUrl);

        return wordsCount;
    }

    private String htmlTagRemove(String rawPageText) {
        Document parsedDoc = Jsoup.parse(rawPageText);
        return parsedDoc.text();
    }

    private List<String> splitString (String textToSplit) {
        List<String> stringList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(textToSplit, DELIMITER);
        while (st.hasMoreTokens()) {
            stringList.add(st.nextToken().toLowerCase());
        }

        return stringList;
    }

    private void fillMap (Map<String, Integer> wordsCount, List<String> stringList){
        stringList.forEach(word -> {
            int count = 1;
            if (!wordsCount.containsKey(word)) {
                wordsCount.put(word, count);
            } else {
                wordsCount.put(word, wordsCount.get(word) + count);
            }
        });
    }
}
