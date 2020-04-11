package com.haixiang.reconsitution.chapter1.after;

import com.haixiang.reconsitution.chapter1.Performance;
import com.haixiang.reconsitution.chapter1.Plays;

public class PerformanceCalculator {
    private Performance performance;
    private Plays plays;

    public PerformanceCalculator(Performance performance, Plays plays) {
        this.performance = performance;
        this.plays = plays;
    }



    public int amount() {
        return -1;
    }

    public int volumeCredit() {
        return Math.max(performance.getAudience() - 30, 0);
    }
}
