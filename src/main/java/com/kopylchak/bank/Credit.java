package com.kopylchak.bank;

import java.io.Serializable;

public class Credit implements Serializable{
    private String creditType;

    private Double interestRate;

    private Integer loanTerm;

    private Integer loanAmount;

    private Boolean isEarlyRepayment;

    private Boolean isLineCreditExtension;

    public Credit(String creditType) {
        this.creditType = creditType;
    }

    public Credit() { }

    public Credit setCreditType(String creditType) {
        this.creditType = creditType;
        return this;
    }

    public Credit setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public Credit setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
        return this;
    }

    public Credit setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
        return this;
    }

    public Credit setEarlyRepayment(Boolean isEarlyRepayment) {
        this.isEarlyRepayment = isEarlyRepayment;
        return this;
    }

    public String getCreditType() {
        return creditType;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public Boolean isEarlyRepayment() {
        return isEarlyRepayment;
    }

    public Boolean isLineCreditExtension() {
        return isLineCreditExtension;
    }

    public Credit setLineCreditExtension(boolean lineCreditExtension){
        this.isLineCreditExtension = lineCreditExtension;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return creditType.equals(credit.creditType);
    }

    @Override
    public int hashCode() {
        return creditType.hashCode();
    }

    @Override
    public String toString() {
        return "[ creditType : " + creditType + ", interestRate : " + interestRate + ", loanTerm : " + loanTerm +
                ", loanAmount : " + loanAmount + ", isEarlyRepayment : " + isEarlyRepayment + ", isLineCreditExtension : " +
                isLineCreditExtension + "]";
    }
}