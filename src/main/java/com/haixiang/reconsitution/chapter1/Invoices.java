package com.haixiang.reconsitution.chapter1;

import lombok.Data;

@Data
public class Invoices {
    private String playID;
    private Integer audience;

    public Invoices(String playID, Integer audience) {
        this.playID = playID;
        this.audience = audience;
    }
}
