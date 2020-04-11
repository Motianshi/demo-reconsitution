package com.haixiang.reconsitution.chapter1.after;

import com.haixiang.reconsitution.chapter1.Performance;
import lombok.Data;

import java.util.List;

@Data
public class StateResultData {
    private String customer;
    private List<Performance> performances;
    private int totalAmount;
    private int volumeCredits;

}
