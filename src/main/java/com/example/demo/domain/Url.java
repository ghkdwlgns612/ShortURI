package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "URLSTORE")
public class Url {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private BigInteger id;
        @Column(nullable = false)
        private String hashvalue; //10글자
        @Column(nullable = false)
        private String originurl;

        @Builder
        public Url(String hashvalue, String originurl) {
                this.hashvalue = hashvalue;
                this.originurl = originurl;
        }
}