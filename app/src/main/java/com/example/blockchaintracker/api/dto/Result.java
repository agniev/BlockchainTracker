package com.example.blockchaintracker.api.dto;


public class Result {

    private String resultCode;

    private String resultDescription;

    public Result(String resultCode, String resultDescription) {
        this.resultCode = resultCode;
        this.resultDescription = resultDescription;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return resultCode != null && resultCode.equals(result.resultCode);
    }

    @Override
    public int hashCode() {
        return resultCode != null ? resultCode.hashCode() : 0;
    }

    public static Result ok() {
        return new Result("0", "");
    }

    public static Result error() {
        return new Result("1", "Error #345");
    }
}
