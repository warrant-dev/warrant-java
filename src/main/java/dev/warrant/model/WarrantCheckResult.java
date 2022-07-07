package dev.warrant.model;

public class WarrantCheckResult {
    
    private Integer code;
    private String result;

    public WarrantCheckResult() {
        // For json serialization
    }

    public WarrantCheckResult(Integer code, String result) {
        this.code = code;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
