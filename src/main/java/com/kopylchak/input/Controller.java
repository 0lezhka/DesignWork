package com.kopylchak.input;

import java.util.*;

import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.client_commands.*;
import com.kopylchak.commands.Command;
import com.kopylchak.commands.*;
import com.kopylchak.commands.credit_commands.*;
import com.kopylchak.commands.bank_commands.*;

public class Controller {
    private BankManager bankManager;
    private Set<Command> commandSet;

    public Controller(){
        this.bankManager = new BankManager();
        commandSet = new HashSet<>();
        commandSet.addAll(Arrays.asList(
                new AddBank(),
                new BankList(),
                new DeleteBank(),
                new Load(),
                new LoadAs(),
                new Save(),
                new SaveAs(),
                new CreditList(),
                new AddClient(),
                new ClientList(),
                new DeleteClient(),
                new AddCredit(),
                new DeleteCredit(),
                new BestCredits(),
                new Help(),
                new BankHelp(),
                new ClientHelp(),
                new CreditHelp(),
                new AllCreditsList(),
                new SeekCredit()));
    }

    public void run(){
        String command;
        ScannerWrapper in = ScannerWrapper.getInstance();

        System.out.print("Enter command -> ");
        while(!(command = in.scanner().nextLine()).matches("\\s*[eE][xX][iI][tT]\\s*")){
            try {
                if(!executeCommand(command))
                    throw new InvalidCommand();
            }
            catch(InvalidCommand e){
                System.err.println(e.getMessage());
            }
            printLine();
            System.out.print("Enter command -> ");
        }
    }

    private boolean executeCommand(String command){
        boolean isValid = false;

        for(Command a : commandSet){
            if(a.matches(command)){
                isValid = true;
                a.ExecuteCommand(bankManager, command);
                break;
            }

        }

        return isValid;
    }

    private void printLine(){
        for(int i = 0; i < 120; i++)
            System.out.print("-");
        System.out.println("");
    }
}
