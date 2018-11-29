package com.kopylchak.commands;

import com.kopylchak.bank.BankManager;

public class Help implements Command {
    String regex = "\\s*[hH][eE][lL][pP]\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        System.out.println("Available commands :");
        System.out.println("Bank add [bankName]");
        System.out.println("Bank list");
        System.out.println("Bank delete [bankName]");
        System.out.println("Bank help");
        System.out.println("Bank Load");
        System.out.println("Bank Save\n");

        System.out.println("Client add [bankName] [clientName]");
        System.out.println("Client list [bankName]");
        System.out.println("Client delete [bankName] [clientName]");
        System.out.println("Client help\n");

        System.out.println("Credit add [bankName] [creditType] [interestRate] [loanTerm] [loanAmount] [isEarlyRepayment] [isLineCreditExtension]");
        System.out.println("Credit best [quality]");
        System.out.println("Credit list [bankName]");
        System.out.println("Credit list");
        System.out.println("Credit delete [bankName] [creditType]");
        System.out.println("Credit seek [bankName] [creditType] [isEarlyRepayment] [isLineExtension] [quality] [minValue] [maxValue] ; * - to pick any");
        System.out.println("Credit help");

        System.out.println("\nExit");

        System.out.println("\nqualities : INTEREST_RATE, LOAN_TERM, LOAN_AMOUNT");
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
