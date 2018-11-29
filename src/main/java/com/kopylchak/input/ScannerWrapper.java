package com.kopylchak.input;

import java.util.Scanner;

public class ScannerWrapper {
    private static ScannerWrapper instance = null;
    private static Scanner scanner = new Scanner(System.in);

    private ScannerWrapper(){}

    public static synchronized ScannerWrapper getInstance() {
        if(instance == null)return new ScannerWrapper();

        return instance;
    }

    public Scanner scanner(){ return scanner;}
}
