package com.example.demo.controller;


import com.example.demo.entity.TargetUrl;
import com.example.demo.model.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatabaseController {

    @Autowired
    private DataService dataService;

    @Operation(summary = "Find by ID", description = "Find one record in database by its ID")
    @GetMapping("/findUrlById/{id}")
    public TargetUrl findById(@PathVariable Long id) {
        return dataService.findById(id);
    }

    @Operation(summary = "Find by URL name", description = "Find one record in database by its URL")
    @PostMapping("/findUrlByName/")
    public TargetUrl findByName(@RequestBody
                                @Parameter(name = "URL",
                                        description = "Action \"Find\" will be performed on this URL",
                                        required = true,
                                        example = "https://www.simbirsoft.com/")
                                String name) {
        return dataService.findByName(name);
    }

    @Operation(summary = "Find all", description = "Find all records in database")
    @GetMapping("/allUrls/")
    public List<TargetUrl> findAll() {
        return dataService.allUrlList();
    }

    @Operation(summary = "Delete by ID", description = "Delete one record in database by its ID")
    @DeleteMapping("/deleteUrlById/{id}")
    public void deleteById(@PathVariable Long id) {
        dataService.deleteById(id);
    }
}
