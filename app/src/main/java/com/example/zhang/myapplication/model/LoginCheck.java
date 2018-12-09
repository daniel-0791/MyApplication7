package com.example.zhang.myapplication.model;

public class LoginCheck {
    public String regFlag=null;
    public String regMsg=null;

    public String getRegFlag() {
        return regFlag;
    }

    public void setRegFlag(String regFlag) {
        this.regFlag = regFlag;
    }

    public String getRegMsg() {
        return regMsg;
    }

    public void setRegMsg(String regMsg) {
        this.regMsg = regMsg;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "regMsg='" + regMsg + '\'' +
                ", regFlag='" + regFlag + '\'' +

                '}';
    }
}
