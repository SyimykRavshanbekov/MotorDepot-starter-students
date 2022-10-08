package com.company.entities;

public class Truck {
    private int id;
    private String truckName;
    private String driver;
    private State State;


    public Truck(int id, String truckName, String driver, State State) {
        this.id = id;
        this.truckName = truckName;
        this.driver = driver;
        this.State = State;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public com.company.entities.State getState() {
        return State;
    }

    public void setState(com.company.entities.State state) {
        State = state;
    }

    @Override
    public String toString() {
        return id + "  |" + truckName + "     |" + State + "      |" + driver;
    }

}

