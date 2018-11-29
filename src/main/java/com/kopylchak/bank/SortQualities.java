package com.kopylchak.bank;

import java.util.Comparator;

public enum SortQualities {
    INTEREST_RATE((o1, o2) -> {
        return o2.getInterestRate().
                compareTo(o1.getInterestRate());
    },"INTEREST_RATE" ),

    LOAN_TERM((o1, o2) -> {
        return o1.getLoanTerm().
                compareTo(o2.getLoanTerm());
    },"LOAN_TERM" ),

    LOAN_AMOUNT((o1, o2) -> {
        return o1.getLoanAmount().
                compareTo(o2.getLoanAmount());
    },"LOAN_AMOUNT" );

    private Comparator<Credit> comparator;
    private String qualityString;

    SortQualities(Comparator<Credit> comparator, String qualityString){
        this.qualityString = qualityString;
        this.comparator = comparator;
    }

    public Comparator<Credit> getComparator() {
        return comparator;
    }

    public String getQualityString(){return qualityString;}

    public static SortQualities getQualityByString(String str){
        for(SortQualities qualities : values()){
            if(str.equalsIgnoreCase(qualities.qualityString))
                return qualities;
        }
        return null;
    }
}
