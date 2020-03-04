package com.example.google_maps;

public class Common {

    private String user_id;
    private String device_id;
    private String timestamp;
    private String location;
    private float lsb;
    private String speed;
    private float heading;
    private float engine;
    private float door;
    private String fuel;
    private String temperature;
    private String ins_timestamp;


    // Getter Methods

    public String getUser_id() {
        return user_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    public float getLsb() {
        return lsb;
    }

    public String getSpeed() {
        return speed;
    }

    public float getHeading() {
        return heading;
    }

    public float getEngine() {
        return engine;
    }

    public float getDoor() {
        return door;
    }

    public String getFuel() {
        return fuel;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIns_timestamp() {
        return ins_timestamp;
    }

    // Setter Methods

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLsb(float lsb) {
        this.lsb = lsb;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setHeading(float heading) {
        this.heading = heading;
    }

    public void setEngine(float engine) {
        this.engine = engine;
    }

    public void setDoor(float door) {
        this.door = door;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setIns_timestamp(String ins_timestamp) {
        this.ins_timestamp = ins_timestamp;
    }
}