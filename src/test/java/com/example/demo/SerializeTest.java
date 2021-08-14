package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
public class SerializeTest {

    Logger log = (Logger) LoggerFactory.getLogger(SerializeTest.class);

    @Test
    public void Test1() throws IOException {
        Member member = new Member("황지훈","ghkdwlgns612@naver.com",27);
        byte[] serializeMember;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        log.info("baos = {}", baos);
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        log.info("oos = {}", oos);
        oos.writeObject(member);
        serializeMember = baos.toByteArray();
        log.info("serializeMember = {}",serializeMember);
    }
    @Test
    public void Test2() {
        Member member = new Member("김배민", "deliverykim@baemin.com", 25);
        // member객체를 csv로 변환
        String csv = String.format("%s,%s,%d",member.getName(), member.getEmail(), member.getAge());
        System.out.println(csv);
    }
}

class Member implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    private String email;
    private int age;

    public Member(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    @Override
    public String toString() {
        return String.format("Member{name='%s', email='%s', age='%s'}", name, email, age);
    }
}