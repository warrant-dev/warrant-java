package dev.warrant.model;

public class WarrantCheck {
    
    private Integer code;
    private String result;

    public WarrantCheck() {
        // For json serialization
    }

    public WarrantCheck(Integer code, String result) {
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

    public boolean isAuthorized() {
        if (code != null) {
            return code == 200;
        }
        return false;
    }
}
