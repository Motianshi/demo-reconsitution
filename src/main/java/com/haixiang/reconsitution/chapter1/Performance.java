package com.haixiang.reconsitution.chapter1;

import lombok.Data;

@Data
public class Performance {
    private String playID;
    private Integer audience;

    public Performance(String playID, Integer audience) {
        this.playID = playID;
        this.audience = audience;
    }
}
