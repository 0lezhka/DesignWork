package com.kopylchak.search;

import com.kopylchak.bank.Bank;
import com.kopylchak.bank.Credit;
import com.kopylchak.bank.SortQualities;

import java.util.*;

public class SearchEngine {
    private List<Bank> bankList;

    public SearchEngine(List<Bank> bankList) {
        this.bankList = bankList;
    }

    public Map<Bank, Credit> getBestCreditsByQuality(SortQualities comparator) {
        Map<Bank, Credit> bestCredits = new HashMap<>();

        for (Bank a : bankList) {
            bestCredits.put(a, Collections.max(a.getCreditList(), comparator.getComparator()));
        }

        return bestCredits;
    }

    public Map<Bank, List<Credit>> getCreditsByType(String type) {
        Map<Bank, List<Credit>> creditMap = new HashMap<>();

        for (Bank a : bankList) {
            creditMap.put(a, new ArrayList<>());
            for (Credit b : a.getCreditList()) {
                if (b.getCreditType().equalsIgnoreCase(type)) {
                    creditMap.get(a).add(b);
                }
            }
            if(creditMap.get(a).size() == 0)
                creditMap.remove(a);
        }

        if (creditMap.size() == 0) {
            throw new RuntimeException("Wrong credit type");
        }

        return creditMap;
    }

    public Map<Bank, List<Credit>> getCreditsByQuality(SortQualities quality, Double rangeBegin, Double rangeEnd) {
        Map<Bank, List<Credit>> creditMap = new HashMap<>();

        for (Bank a : bankList) {
            creditMap.put(a, getCreditsInRange(quality, a, rangeBegin, rangeEnd));
        }

        return creditMap;
    }

    public Map<Bank, List<Credit>> getCreditsByEarlyRepayment(Boolean isEarlyRepayment) {
        Map<Bank, List<Credit>> creditMap = new HashMap<>();

        for (Bank a : bankList) {
            creditMap.put(a, new ArrayList<>());
            for (Credit b : a.getCreditList()) {
                if (b.isEarlyRepayment().equals(isEarlyRepayment))
                    creditMap.get(a).add(b);
            }
        }
        return creditMap;
    }

    public Map<Bank, List<Credit>> getCreditsByLineExtension(Boolean isEarlyLineCreditExtension) {
        Map<Bank, List<Credit>> creditMap = new HashMap<>();

        for (Bank a : bankList) {
            creditMap.put(a, new ArrayList<>());
            for (Credit b : a.getCreditList()) {
                if (b.isLineCreditExtension().equals(isEarlyLineCreditExtension))
                    creditMap.get(a).add(b);
            }
        }
        return creditMap;
    }

    // вертає список кредитів банку, які потрапляють в певний діапазон
    private List<Credit> getCreditsInRange(SortQualities quality, Bank bank, Double rangeBegin, Double rangeEnd) {
        List<Credit> list = new ArrayList<>();

        for (Credit a : bank.getCreditList()) {
            switch (quality) {
                case INTEREST_RATE:
                    if (a.getInterestRate() >= rangeBegin && a.getInterestRate() <= rangeEnd)
                        list.add(a);
                    break;

                case LOAN_TERM:
                    if (a.getLoanTerm() >= rangeBegin && a.getLoanTerm() <= rangeEnd)
                        list.add(a);
                    break;

                case LOAN_AMOUNT:
                    if (a.getLoanAmount() >= rangeBegin && a.getLoanAmount() <= rangeEnd)
                        list.add(a);
                    break;
            }
        }

        return list;
    }
}
