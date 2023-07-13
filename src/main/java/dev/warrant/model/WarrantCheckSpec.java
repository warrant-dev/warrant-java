package dev.warrant.model;

import java.util.List;

public class WarrantCheckSpec {

    private List<WarrantSpec> warrants;
    private String op;

    public WarrantCheckSpec() {
        // For json serialization
    }

    public WarrantCheckSpec(List<WarrantSpec> warrants, String op) {
        this.warrants = warrants;
        this.op = op;
    }

    public WarrantCheckSpec(List<WarrantSpec> warrants) {
        this.warrants = warrants;
    }

    public List<WarrantSpec> getWarrants() {
        return warrants;
    }

    public void setWarrants(List<WarrantSpec> warrants) {
        this.warrants = warrants;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
