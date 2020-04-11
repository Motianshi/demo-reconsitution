package com.haixiang.reconsitution.chapter1.after;

import com.haixiang.reconsitution.chapter1.Performance;
import com.haixiang.reconsitution.chapter1.Plays;

public class TragedyCalculator extends PerformanceCalculator{

    private Performance performance;

    public TragedyCalculator(Performance performance, Plays plays) {
        super(performance, plays);
    }

    public int amount() {
        int result = 40000;
        if (performance.getAudience() > 30) {
            result += 1000 * (performance.getAudience() - 30);
        }
        return result;
    }
}
