package com.travel.backpacker.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String line1;

    @Column
    private String line2;

    @Column
    private String county;

    @Column
    private String city;

    @Column
    private String postcode;

    public Address(Long id, String line1, String line2, String county, String city, String postcode) {
        this.id = id;
        this.line1 = line1;
        this.line2 = line2;
        this.county = county;
        this.city = city;
        this.postcode = postcode;
    }

    public Address() {
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

