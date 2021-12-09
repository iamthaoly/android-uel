package com.example.model;

public class Service {
    private int wId;
    private String wName;
    private String wDes;
    private byte[] wThumb;

    public Service(int wId, String wName, String wDes, byte[] wThumb) {
        this.wId = wId;
        this.wName = wName;
        this.wDes = wDes;
        this.wThumb = wThumb;
    }

    public int getwId() {
        return wId;
    }

    public void setwId(int wId) {
        this.wId = wId;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getwDes() {
        return wDes;
    }

    public void setwDes(String wDes) {
        this.wDes = wDes;
    }

    public byte[] getwThumb() {
        return wThumb;
    }

    public void setwThumb(byte[] wThumb) {
        this.wThumb = wThumb;
    }
}
