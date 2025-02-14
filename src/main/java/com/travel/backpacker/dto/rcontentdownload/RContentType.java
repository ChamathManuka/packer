package com.travel.backpacker.dto.rcontentdownload;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RContentType implements Serializable
{

    @NotNull(message = "Type should not be empty")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
