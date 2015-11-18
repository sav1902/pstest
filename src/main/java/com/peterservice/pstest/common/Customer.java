package com.peterservice.pstest.common;

import java.util.List;

/**
 * Created by Andrey.Shilov on 17.11.2015.
 */
public class Customer {
    private Long id;
    private String name;
    private String city;
    private List<Subscriber> subscribers;

    public Customer(){}

    public Customer(Long id, String name, String city, List<Subscriber> subscribers) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.subscribers = subscribers;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
