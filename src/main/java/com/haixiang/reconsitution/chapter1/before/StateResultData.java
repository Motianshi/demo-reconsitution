package com.haixiang.reconsitution.chapter1.before;

import com.haixiang.reconsitution.chapter1.Invoices;
import lombok.Data;

import java.util.List;

@Data
public class StateResultData {
    private String customer;
    private List<Invoices> performances;
    private int totalAmount;
    private int volumeCredits;

}
