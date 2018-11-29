package com.kopylchak.bank;

import java.io.Serializable;

public class Client implements Comparable, Serializable {
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return name.equals(client.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        Client other = (Client) o;
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "name : " + name;
    }
}

