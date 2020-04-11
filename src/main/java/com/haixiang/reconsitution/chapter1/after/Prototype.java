package com.haixiang.reconsitution.chapter1.after;

import com.haixiang.reconsitution.chapter1.Performance;
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
        List<Performance> performanceList = Arrays.asList(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40));
        invoicesMap.put("performances", performanceList);
    }

    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        System.out.println(prototype.htmlStatement());
    }

    private String statement() {
        return renderPlainText(createStatementData());
    }
    private String htmlStatement() {
        return htmmRenderPlainText(createStatementData());
    }

    private StateResultData createStatementData() {
        StateResultData resultData = new StateResultData();
        resultData.setCustomer((String) invoicesMap.get("customer"));
        resultData.setPerformances((List<Performance>) invoicesMap.get("performances"));
        resultData.setTotalAmount(totalAmounts());
        resultData.setVolumeCredits(totalVolumeCredits());
        return resultData;
    }

    private String renderPlainText(StateResultData statementData) {
        String result = "Statement for " + statementData.getCustomer() + "\n";
        for (Performance perf : statementData.getPerformances()) {
            result += "  " +playFor(perf).getName() + ": " + amountFor(perf) / 100 + "￥(" + perf.getAudience() + " seats)\n";
        }
        result += "Amount owed is " + statementData.getTotalAmount() / 100 + "￥ \n";
        result += "You earned " + statementData.getVolumeCredits() + " credits\n";
        return result;
    }
    private String htmmRenderPlainText(StateResultData statementData) {
        String result = "Statement for " + statementData.getCustomer() + "\n";
        result += "<table>\n";
        result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>\n";
        for (Performance perf : statementData.getPerformances()) {
            result += "<tr><td>" + playFor(perf).getName() + "</td><td>" + perf.getAudience() + "</td>";
            result += "<td>" + +amountFor(perf) / 100 + "￥</td></tr>\n";
        }
        result += "</table>\n";
        result += "<p>Amount owed is <em>" + statementData.getTotalAmount() / 100 + "￥</em></p> \n";
        result += "<p>You earned <em> " + statementData.getVolumeCredits() + "</em> credits</p>\n";
        return result;
    }

    private int totalAmounts() {
        int totalAmount = 0;
        for (Performance perf : (List<Performance>) invoicesMap.get("performances")) {
            totalAmount += amountFor(perf);
        }
        return totalAmount;
    }

    /**\
     * 计算积分
     * @return
     */
    private int totalVolumeCredits() {
        int volumeCredits = 0;
        for (Performance perf : (List<Performance>) invoicesMap.get("performances")) {
            volumeCredits = volumeCreditsFor(perf);
        }
        return volumeCredits;
    }

    private Plays playFor(Performance perf) {
        return playsMap.get(perf.getPlayID());
    }
    private int volumeCreditsFor(Performance perf) {
       return new PerformanceCalculator(perf, playFor(perf)).volumeCredit();
    }
    private int amountFor(Performance perf) {
        return new PerformanceCalculator(perf, playFor(perf)).amount();
    }
}
