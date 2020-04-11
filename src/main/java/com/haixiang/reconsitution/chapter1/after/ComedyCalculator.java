package com.haixiang.reconsitution.chapter1.after;

import com.haixiang.reconsitution.chapter1.Performance;
import com.haixiang.reconsitution.chapter1.Plays;

public class ComedyCalculator extends PerformanceCalculator{

    private Performance performance;

    public ComedyCalculator(Performance performance, Plays plays) {
        super(performance, plays);
    }

    public int amount() {
        int result = 30000;
        if (performance.getAudience() > 20) {
            result += 10000 + 500 * (performance.getAudience() - 20);
        }
        result += 300 * performance.getAudience();
        return result;
    }

    public int volumeCredit() {
        return (int) (super.volumeCredit() + Math.floor(performance.getAudience() / 5));
    }

}
