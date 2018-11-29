package com.kopylchak.commands;

import com.kopylchak.bank.BankManager;

public interface Command {
    void ExecuteCommand(BankManager bankManager, String str);

    boolean matches(String str);
}
