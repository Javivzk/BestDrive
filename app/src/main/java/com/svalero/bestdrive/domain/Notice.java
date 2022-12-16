package com.svalero.bestdrive.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Notice {
    @PrimaryKey
    @NonNull
    private String name;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private String publisher;
    @ColumnInfo
    private boolean verify;
    @ColumnInfo
    private double latitude;
    @ColumnInfo
    private double longitude;

    public Notice(){

    }

    public Notice(String name, String description, String publisher, boolean verify, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.publisher = publisher;
        this.verify = verify;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



}
