package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "URISTORE")
public class Uri {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @Column(nullable = false)
    private String originuri;
    @Column(nullable = false)
    private String changeduri;

    @Builder
    public Uri(String originuri, String changeduri) {
        this.originuri = originuri;
        this.changeduri = changeduri;
    }
}
