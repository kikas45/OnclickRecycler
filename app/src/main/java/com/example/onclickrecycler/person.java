package com.example.onclickrecycler;
// Your package name can be different depending
// on your project name

public class person {
 String firstname, lastname, age, image;

    public person() {
    }

    public person(String firstname, String lastname, String age, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.image = image;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}