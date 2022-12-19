package dev.warrant.model;

import java.util.List;

public class WarrantCheckSpec {
    
    private List<Warrant> warrants;
    private String op;

    public WarrantCheckSpec() {
        // For json serialization
    }

    public WarrantCheckSpec(List<Warrant> warrants, String op) {
        this.warrants = warrants;
        this.op = op;
    }

    public WarrantCheckSpec(List<Warrant> warrants) {
        this.warrants = warrants;
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
