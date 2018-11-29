package com.kopylchak.commands;

public class InvalidCommand extends RuntimeException {
    @Override
    public String getMessage() {
        return "invalid command, please use \"help\" to see all available commands";
    }
}
