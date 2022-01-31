package com.example.demo.repo;

import com.example.demo.entity.Words;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordsRepository extends JpaRepository<Words, Long> {
}
