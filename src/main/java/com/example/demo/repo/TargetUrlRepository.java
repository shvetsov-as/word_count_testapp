package com.example.demo.repo;

import com.example.demo.entity.TargetUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetUrlRepository extends JpaRepository<TargetUrl, Long> {

    TargetUrl findByName(String name);
}
