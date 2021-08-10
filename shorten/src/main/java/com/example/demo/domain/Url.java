package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "URLSTORE")
public class Url {
        @Id
        @Column(nullable = false)
        private String hashvalue;
        @Column(nullable = false)
        private String originurl;
        @Builder
        public Url(String hashvalue, String originurl) {
                this.hashvalue = hashvalue;
                this.originurl = originurl;
        }
}