package com.sreejith.mongodbspringboot;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("collection=PlayerCollection")
public class Player {

    public Player(){}

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    LocalDateTime dob;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private Integer age;

    public SkillSetEnum getSkill() {
        return skill;
    }

    public void setSkill(SkillSetEnum skill) {
        this.skill = skill;
    }

    private SkillSetEnum skill;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String country;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Id
    private String playerId;
}
