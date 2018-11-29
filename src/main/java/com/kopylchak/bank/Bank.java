package com.kopylchak.bank;

import java.beans.Expression;
import java.io.*;
import java.util.*;

public class Bank implements Serializable {
    private String name;

    private List<Credit> creditList = new ArrayList<>();

    private Map<Client, ClientData> clientMap = new TreeMap<>();

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addClient(String name) {
        try {
            if (clientMap.containsKey(new Client(name)))
                throw new Exception("client_commands already exist");
            clientMap.put(new Client(name), null);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteClient(String name) {
        try {
            if (!clientMap.containsKey(new Client(name)))
                throw new Exception("client_commands doesn't exist");
            clientMap.remove(new Client(name));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void addCredit(Credit credit) {
        creditList.add(credit);
    }

    public void deleteCredit(String creditType) {
        if(!creditList.remove(new Credit(creditType)))
            throw new RuntimeException("Invalid credit type");
    }

    public void takeCredit(String clientName, String creditType, Integer loanTerm, Integer loanAmount) {
        Client client = null;
        Credit credit = null;

        try {
            if (creditList.contains(new Credit(creditType))) {
                credit = new Credit(creditType);
            } else throw new Exception("credit_commands doesn't exist");

            if (clientMap.containsKey(new Client(clientName))) {
                client = new Client(clientName);
            } else throw new Exception("client_commands doesn't exist");
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        ClientData clientData = new ClientData(credit, loanTerm, loanAmount);

        clientMap.put(client, clientData);
    }

    public void pay(String clientName) {
        clientMap.get(new Client(clientName)).pay();

        if (clientMap.get(new Client(clientName)).credit == null) {
            closeCredit(clientName);
        }
    }

    public void closeCredit(String clientName) {
        clientMap.get(new Client(clientName)).closeCredit();
        clientMap.put(new Client(clientName), null);
    }

    public List<Credit> getCreditList() {
        return creditList;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getName().equalsIgnoreCase(((Bank) obj).getName());
    }

    public Map<Client, ClientData> getClientMap() {
        return clientMap;
    }
}

