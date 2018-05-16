package com.example.blockchaintracker.presentation.tracker_user;

public class TrackingData {

    private int id;

    private String gpsLongitude;

    private String gpsLatitude;

    private String date;

    private String hash;

    private String previousHash;

    private long blockId;

    private long nonce;

    public TrackingData(int id, String gpsLongitude, String gpsLatitude, String date, String hash) {
        this.id = id;
        this.gpsLongitude = gpsLongitude;
        this.gpsLatitude = gpsLatitude;
        this.date = date;
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public String getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(String gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getBlockId() {
        return blockId;
    }

    public void setBlockId(long blockId) {
        this.blockId = blockId;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }
}
