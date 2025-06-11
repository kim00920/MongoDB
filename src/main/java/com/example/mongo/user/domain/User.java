package com.example.mongo.user.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
public class User {

    @Id
    private String id;

    private String name;
    private int age;
}
