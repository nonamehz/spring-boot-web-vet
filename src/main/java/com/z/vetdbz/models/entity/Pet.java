package com.z.vetdbz.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para que se autogenere automaticamente el pk
    private Integer id;
    @NotNull
    private Integer age;
    @NotBlank
    private String name;
    @NotBlank
    private String breed;
    @NotBlank
    private String color;
    @NotBlank
    private String gender;
    @NotBlank
    private String birthday;
    @NotBlank
    private String owner;
    @NotNull
    @DecimalMin("0.0")
    private Double weigh;
    private String urlImg;

    public Pet() {
    }

    public Pet(Integer id, Integer age, String owner, String name, String breed, String color, String gender,
            String birthday, String urlImg, Double weigh) {
        this.id = id;
        this.age = age;
        this.owner = owner;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.birthday = birthday;
        this.urlImg = urlImg;
        this.weigh = weigh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Double getWeigh() {
        return weigh;
    }

    public void setWeigh(Double weigh) {
        this.weigh = weigh;
    }
}
