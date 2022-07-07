package dev.warrant.model;

import java.util.List;

public class WarrantCheck {
    
    private List<Warrant> warrants;
    private String op;

    public WarrantCheck() {
        // For json serialization
    }

    public WarrantCheck(List<Warrant> warrants, String op) {
        this.warrants = warrants;
        this.op = op;
    }

    public List<Warrant> getWarrants() {
        return warrants;
    }

    public void setWarrants(List<Warrant> warrants) {
        this.warrants = warrants;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
