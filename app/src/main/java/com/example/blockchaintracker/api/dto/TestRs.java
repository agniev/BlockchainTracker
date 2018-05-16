package com.example.blockchaintracker.api.dto;

public class TestRs {
    private long id;

    private String hash;

    private String previous_hash;

    private String info;

    private long nonce;

    public TestRs(long id, String hash, String previous_hash, String info, long nonce) {
        this.id = id;
        this.hash = hash;
        this.previous_hash = previous_hash;
        this.info = info;
        this.nonce = nonce;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrevious_hash() {
        return previous_hash;
    }

    public void setPrevious_hash(String previous_hash) {
        this.previous_hash = previous_hash;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }
}
