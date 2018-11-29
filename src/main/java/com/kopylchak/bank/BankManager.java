package com.kopylchak.bank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankManager {
    private List<Bank> bankList = new ArrayList<>();

    public void addBank(String name) {
        bankList.add(new Bank(name));
    }

    public void deleteBank(String name) {
        if(!bankList.remove(new Bank(name)))
            throw new RuntimeException("Bank doesn't exist");
    }

    public void writeBankInFile(String fileName) {
        String file;

        if(fileName != null){
            file = "data\\" + fileName + ".out";
        }else {
            file = "data\\bankInfo.out";
        }

        new File("data").mkdir();
        FileOutputStream fOut = null;
        ObjectOutputStream oOut = null;

        try {
            fOut = new FileOutputStream(file);
            oOut = new ObjectOutputStream(fOut);

            oOut.writeObject(bankList);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                oOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadBankFromFile(String fileName) {
        FileInputStream fInput = null;
        ObjectInputStream oInput = null;

        if(fileName == null){
            fileName = "data\\bankInfo.out";
        }else {
            fileName = "data\\" + fileName + ".out";
        }

        try {
            fInput = new FileInputStream(fileName);
            oInput = new ObjectInputStream(fInput);
            bankList = (List) oInput.readObject();
        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            try {
                fInput.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            try {
                oInput.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public Bank getBankByName(String name){
        for(Bank bank : bankList){
            if(bank.getName().equalsIgnoreCase(name))
                return bank;
        }
            throw new RuntimeException("Bank doesn't exist");
    }
}
