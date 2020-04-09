package com.haixiang.reconsitution.chapter1.after;

import com.haixiang.reconsitution.chapter1.Invoices;
import com.haixiang.reconsitution.chapter1.Plays;
import com.haixiang.reconsitution.chapter1.before.StateResultData;

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
        return renderPlainText(createStatementData());
    }
    private String htmlStatement() {
        return htmmRenderPlainText(createStatementData());
    }

    private StateResultData createStatementData() {
        StateResultData resultData = new StateResultData();
        resultData.setCustomer((String) invoicesMap.get("customer"));
        resultData.setPerformances((List<Invoices>) invoicesMap.get("performances"));
        resultData.setTotalAmount(totalAmounts());
        resultData.setVolumeCredits(totalVolumeCredits());
        return resultData;
    }

    private String renderPlainText(StateResultData statementData) {
        String result = "Statement for " + statementData.getCustomer() + "\n";
        for (Invoices perf : statementData.getPerformances()) {
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
        for (Invoices perf : statementData.getPerformances()) {
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
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
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
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            if ("comedy".equals(playFor(perf).getType())) {
                volumeCredits += Math.floor(perf.getAudience() / 5);
            }
        }
        return volumeCredits;
    }

    private Plays playFor(Invoices perf) {
        return playsMap.get(perf.getPlayID());
    }

    private int amountFor(Invoices perf) {
        int result = 0;
        switch (playFor(perf).getType()) {
            case "tragedy":
                result = 40000;
                if (perf.getAudience() > 30) {
                    result += 1000 * (perf.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (perf.getAudience() > 20) {
                    result += 10000 + 500 * (perf.getAudience() - 20);
                }
                result += 300 * perf.getAudience();
                break;
            default:
                throw new Error("unknown type");
        }
        return result;
    }
}
