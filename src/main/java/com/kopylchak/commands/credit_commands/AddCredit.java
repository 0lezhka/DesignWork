package com.kopylchak.commands.credit_commands;

import com.kopylchak.bank.Bank;
import com.kopylchak.bank.BankManager;
import com.kopylchak.bank.Credit;
import com.kopylchak.commands.Command;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCredit implements Command {
    String regex = "\\s*[cC][rR][eE][dD][iI][tT]\\s+[aA][dD][dD]\\s+(?<bankName>\\w+)\\s+(?<creditType>\\w+)\\s+(?<interestRate>\\d+" +
            "\\.?\\d*)\\s+(?<loanTerm>\\d+)\\s+(?<loanAmount>\\d+)\\s+" +
            "(?<earlyRepayment>(true)|(false))\\s+(?<lineCreditExtension>(true)|(false))\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {

            Credit credit = new Credit();
            credit.setCreditType(matcher.group("creditType")).setInterestRate(Double.parseDouble(matcher.group("interestRate")))
                    .setLoanTerm(Integer.parseInt(matcher.group("loanTerm"))).setLoanAmount(Integer.parseInt(matcher.group("loanAmount")))
                    .setEarlyRepayment(matcher.group("earlyRepayment").equalsIgnoreCase("true"))
                    .setLineCreditExtension(matcher.group("lineCreditExtension").equalsIgnoreCase("true"));

            bankManager.getBankByName(matcher.group("bankName")).addCredit(credit);
            Logger.getLogger(Credit.class).info(" New Credit" + "(" + matcher.group("bankName") + ") : " + credit);
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
