package com.kopylchak.bank;

import java.io.Serializable;

public class ClientData implements Serializable {
    Credit credit;

    private Double debt;

    private Double monthPayment;

    private Double loanAmount;

    private Integer loanTerm;

    public ClientData(Credit creditType, Integer loanTerm, Integer loanAmount) {
        credit = creditType;

        this.loanTerm = loanTerm;
        this.loanAmount = debt = loanAmount.doubleValue();

        countMonthPayment();
    }

    private void countMonthPayment() {
        monthPayment = loanAmount / loanTerm;
        monthPayment += debt / 12 * credit.getInterestRate() / 100;
    }

    public void pay() {
        System.out.println("ви заплатили " + monthPayment);

        debt -= loanAmount / loanTerm;

        countMonthPayment();

        if (debt == 0) {
            credit = null;
        }
    }

    public void closeCredit() {
        System.out.println(String.format("Ви заплатили %.3f і закрили кредит", debt));
        credit = null;
    }

    public Double getLoanAmount() { return loanAmount; }

    public Integer getLoanTerm() { return loanTerm; }

    public Double getNextPayment(){return monthPayment;}

    @Override
    public String toString() {
        return "debt : " + debt +
                ", nextPayment : " + monthPayment +
                ", loanAmount : "  + loanAmount +
                ", loanTerm : " + loanTerm;
    }
}
