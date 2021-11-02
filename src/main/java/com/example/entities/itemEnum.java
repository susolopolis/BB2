package com.example.entities;

public enum itemEnum {
    ACTIVE (1, "Active"),NO_ACTIVE (0, "No Active");

    int val;
    String Estado;

    private itemEnum(int val, String estado){
        this.val = val;
        this.Estado = estado;
    }

    public itemEnum getEnum(){
        return this;
    }

    public int getVal() {
        return val;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
