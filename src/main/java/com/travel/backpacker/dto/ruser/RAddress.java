package com.travel.backpacker.dto.ruser;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RAddress implements Serializable {
    @NotNull(message = "The address line 1 is mandatory")
    private String line1;

    private String line2;

    private String county;

    @NotNull(message = "The address city is mandatory")
    private String city;

    @NotNull(message = "The address postcode is mandatory")
    private String postcode;

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
}
