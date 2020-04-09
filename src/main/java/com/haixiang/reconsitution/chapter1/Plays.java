package com.haixiang.reconsitution.chapter1;

import lombok.Data;

@Data
public class Plays {
    private String type;
    private String name;

    public Plays(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
