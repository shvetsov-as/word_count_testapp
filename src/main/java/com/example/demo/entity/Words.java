package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "words")
@NoArgsConstructor
@Data
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_word", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private Integer count;

    public Words(String word, Integer count) {
        this.word = word;
        this.count = count;
    }
}
