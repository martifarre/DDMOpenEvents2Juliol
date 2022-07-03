package com.example.ddmopenevents2.business;

public class AssistanceResponse {
    private int fieldCount;
    private int affectedRows;
    private int insertId;
    private String info;
    private int serverStatus;
    private int warningStatus;

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    public int getAffectedRows() {
        return affectedRows;
    }

    public void setAffectedRows(int affectedRows) {
        this.affectedRows = affectedRows;
    }

    public int getInsertId() {
        return insertId;
    }

    public void setInsertId(int insertId) {
        this.insertId = insertId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getWarningStatus() {
        return warningStatus;
    }

    public void setWarningStatus(int warningStatus) {
        this.warningStatus = warningStatus;
    }
}
