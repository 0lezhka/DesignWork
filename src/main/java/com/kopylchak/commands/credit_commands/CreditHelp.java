package com.kopylchak.commands.credit_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;

public class CreditHelp implements Command {
    String regex = "\\s*[cC][rR][eE][dD][iI][tT]\\s+[hH][eE][lL][pP]\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        System.out.println("Credit add [bankName] [creditType] [interestRate] [loanTerm] [loanAmount] [isEarlyRepayment] [isLineCreditExtension]");
        System.out.println("Credit best [quality]");
        System.out.println("Credit list [bankName]");
        System.out.println("Credit list");
        System.out.println("Credit delete [bankName] [creditType]");
        System.out.println("Credit seek [bankName] [creditType] [isEarlyRepayment] [isLineExtension] [quality] [minValue] [maxValue] ; * - to pick any");
        System.out.println("Credit help");

        System.out.println("\nqualities : INTEREST_RATE, LOAN_TERM, LOAN_AMOUNT");
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
