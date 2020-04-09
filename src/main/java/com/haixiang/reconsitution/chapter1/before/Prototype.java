package com.haixiang.reconsitution.chapter1.before;

import com.haixiang.reconsitution.chapter1.Invoices;
import com.haixiang.reconsitution.chapter1.Plays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prototype {
    Map<String, Plays> playsMap = new HashMap<>();
    Map<String, Object> invoicesMap = new HashMap<>();
    {
        //构造剧目数据
        playsMap.put("hamlet", new Plays("tragedy", "Hamlet"));
        playsMap.put("as-like", new Plays("comedy", "As You Like It"));
        playsMap.put("othello", new Plays("tragedy", "Othello"));

        //构造账单数据
        invoicesMap.put("customer", "BigCo");
        List<Invoices> invoicesList = Arrays.asList(
                new Invoices("hamlet", 55),
                new Invoices("as-like", 35),
                new Invoices("othello", 40));
        invoicesMap.put("performances", invoicesList);
    }

    public static void main(String[] args) {
//        System.out.println(statement());
    }

    private String statement() {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = "Statement for " + invoicesMap.get("customer") + "\n";

        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
            Plays play = playsMap.get(perf.getPlayID());
            int thisAmount = 0;
            switch (play.getType()) {
                case "tragedy":
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                    break;
                default:
                    throw new Error("unknown type");
            }
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            if ("comedy".equals(play.getType())) volumeCredits += Math.floor(perf.getAudience() / 5);
            result += "  " +play.getName() + ": " + thisAmount / 100 + "￥(" + perf.getAudience() + " seats)\n";
            totalAmount += thisAmount;
        }
        result += "Amount owed is " + totalAmount / 100 + "￥ \n";
        result += "You earned " + volumeCredits + " credits\n";
        return result;
    }
}
