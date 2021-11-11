package com.company.Summative2KhanSabur.model;

import java.util.Objects;

public class publisher {

    private int publisher_id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String phone;
    private String email;

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof publisher)) return false;
        publisher publisher = (publisher) o;
        return publisher_id == publisher.publisher_id && Objects.equals(name, publisher.name) && Objects.equals(street, publisher.street) && Objects.equals(city, publisher.city) && Objects.equals(state, publisher.state) && Objects.equals(postal_code, publisher.postal_code) && Objects.equals(phone, publisher.phone) && Objects.equals(email, publisher.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisher_id, name, street, city, state, postal_code, phone, email);
    }
}
